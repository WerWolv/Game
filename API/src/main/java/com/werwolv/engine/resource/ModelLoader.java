package com.werwolv.engine.resource;

import com.werwolv.engine.Model;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class ModelLoader {

    private static List<Integer> vaos = new ArrayList<>();
    private static List<Integer> vbos = new ArrayList<>();

    private static int createVAO() {
        int vaoId = GL30.glGenVertexArrays();
        vaos.add(vaoId);
        GL30.glBindVertexArray(vaoId);

        return vaoId;
    }

    private static void bindIndeciesBuffer(int[] indices) {
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
    }

    private static void storeDataInAttribList(int attribNum, int  size, float[] data) {
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, asBuffer(data), GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribNum, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private static FloatBuffer asBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);

        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    private static IntBuffer asBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);

        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    public static void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    public static void clean() {
        vaos.forEach(vao -> GL30.glDeleteVertexArrays(vao));
        vbos.forEach(vbo -> GL15.glDeleteBuffers(vbo));
    }


    public static Model loadOBJ(String objFilePath) {
        FileReader isr;
        String[] tokens = objFilePath.split(":");
        File objFile = new File(ClassLoader.getSystemClassLoader().getResource(tokens[0] + "/models/" + tokens[1] + ".obj").getFile());

        try {
            isr = new FileReader(objFile);
        } catch (FileNotFoundException e) {
            System.err.println("File not found in res; don't use any extention");
            return null;
        }

        BufferedReader reader = new BufferedReader(isr);
        String line;
        List<VertexNM> vertices = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        try {
            while (true) {
                line = reader.readLine().replace("  ", " ");
                if (line.startsWith("v ")) {
                    String[] currentLine = line.split(" ");
                    Vector3f vertex = new Vector3f(Float.valueOf(currentLine[1]),
                            Float.valueOf(currentLine[3]),
                            Float.valueOf(currentLine[2]));
                    VertexNM newVertex = new VertexNM(vertices.size(), vertex);
                    vertices.add(newVertex);
                } else if (line.startsWith("vt ")) {
                    String[] currentLine = line.split(" ");
                    Vector2f texture = new Vector2f(Float.valueOf(currentLine[1]),
                            Float.valueOf(currentLine[2]));
                    textures.add(texture);
                } else if (line.startsWith("vn ")) {
                    String[] currentLine = line.split(" ");
                    Vector3f normal = new Vector3f(Float.valueOf(currentLine[1]),
                            Float.valueOf(currentLine[3]),
                            Float.valueOf(currentLine[2]));
                    normals.add(normal);
                } else if (line.startsWith("f ")) break;
            }

            while (line != null && line.startsWith("f ")) {
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[3].split("/");
                String[] vertex3 = currentLine[2].split("/");
                VertexNM v0 = processVertex(vertex1, vertices, indices);
                VertexNM v1 = processVertex(vertex2, vertices, indices);
                VertexNM v2 = processVertex(vertex3, vertices, indices);
                calculateTangents(v0, v1, v2, textures);//NEW
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file");
        }

        removeUnusedVertices(vertices);
        float[] verticesArray = new float[vertices.size() * 3];
        float[] texturesArray = new float[vertices.size() * 2];
        float[] normalsArray = new float[vertices.size() * 3];
        float[] tangentsArray = new float[vertices.size() * 3];
        float furthest = convertDataToArrays(vertices, textures, normals, verticesArray, texturesArray, normalsArray, tangentsArray);
        int[] indicesArray = convertIndicesListToArray(indices);

        return new Model(verticesArray, texturesArray, indicesArray);//loadToVAO(new ModelDataNM(verticesArray, texturesArray, normalsArray, tangentsArray, indicesArray, furthest));
    }

    private static void calculateTangents(VertexNM v0, VertexNM v1, VertexNM v2, List<Vector2f> textures) {
        Vector3f delatPos1 = new Vector3f();
        Vector3f delatPos2 = new Vector3f();
        v1.getPosition().sub(v0.getPosition(), delatPos1);
        v2.getPosition().sub(v0.getPosition(), delatPos2);

        Vector2f uv0 = textures.get(v0.getTextureIndex());
        Vector2f uv1 = textures.get(v1.getTextureIndex());
        Vector2f uv2 = textures.get(v2.getTextureIndex());
        Vector2f deltaUv1 = new Vector2f();
        Vector2f deltaUv2 = new Vector2f();

        uv1.sub(uv0, deltaUv1);
        uv2.sub(uv0, deltaUv2);

        float r = 1.0f / (deltaUv1.x * deltaUv2.y - deltaUv1.y * deltaUv2.x);
        delatPos1.mul(deltaUv2.y);
        delatPos2.mul(deltaUv1.y);
        Vector3f tangent = new Vector3f();

        delatPos1.sub(delatPos2, tangent);

        tangent.mul(r);
        v0.addTangent(tangent);
        v1.addTangent(tangent);
        v2.addTangent(tangent);
    }

    private static VertexNM processVertex(String[] vertex, List<VertexNM> vertices,
                                          List<Integer> indices) {
        int index = Integer.parseInt(vertex[0]) - 1;
        VertexNM currentVertex = vertices.get(index);
        int textureIndex = Integer.parseInt(vertex[1]) - 1;
        int normalIndex = Integer.parseInt(vertex[2]) - 1;
        if (!currentVertex.isSet()) {
            currentVertex.setTextureIndex(textureIndex);
            currentVertex.setNormalIndex(normalIndex);
            indices.add(index);
            return currentVertex;
        } else {
            return dealWithAlreadyProcessedVertex(currentVertex, textureIndex, normalIndex, indices,
                    vertices);
        }
    }

    private static int[] convertIndicesListToArray(List<Integer> indices) {
        int[] indicesArray = new int[indices.size()];
        for (int i = 0; i < indicesArray.length; i++) {
            indicesArray[i] = indices.get(i);
        }
        return indicesArray;
    }

    private static float convertDataToArrays(List<VertexNM> vertices, List<Vector2f> textures,
                                             List<Vector3f> normals, float[] verticesArray, float[] texturesArray,
                                             float[] normalsArray, float[] tangentsArray) {
        float furthestPoint = 0;
        for (int i = 0; i < vertices.size(); i++) {
            VertexNM currentVertex = vertices.get(i);
            if (currentVertex.getLength() > furthestPoint) {
                furthestPoint = currentVertex.getLength();
            }
            Vector3f position = currentVertex.getPosition();
            Vector2f textureCoord = textures.get(currentVertex.getTextureIndex());
            Vector3f normalVector = normals.get(currentVertex.getNormalIndex());
            Vector3f tangent = currentVertex.getAverageTangent();
            verticesArray[i * 3] = position.x;
            verticesArray[i * 3 + 1] = position.y;
            verticesArray[i * 3 + 2] = position.z;
            texturesArray[i * 2] = textureCoord.x;
            texturesArray[i * 2 + 1] = 1 - textureCoord.y;
            normalsArray[i * 3] = normalVector.x;
            normalsArray[i * 3 + 1] = normalVector.y;
            normalsArray[i * 3 + 2] = normalVector.z;
            tangentsArray[i * 3] = tangent.x;
            tangentsArray[i * 3 + 1] = tangent.y;
            tangentsArray[i * 3 + 2] = tangent.z;

        }
        return furthestPoint;
    }

    private static VertexNM dealWithAlreadyProcessedVertex(VertexNM previousVertex, int newTextureIndex,
                                                           int newNormalIndex, List<Integer> indices, List<VertexNM> vertices) {
        if (previousVertex.hasSameTextureAndNormal(newTextureIndex, newNormalIndex)) {
            indices.add(previousVertex.getIndex());
            return previousVertex;
        } else {
            VertexNM anotherVertex = previousVertex.getDuplicateVertex();
            if (anotherVertex != null) {
                return dealWithAlreadyProcessedVertex(anotherVertex, newTextureIndex,
                        newNormalIndex, indices, vertices);
            } else {
                VertexNM duplicateVertex = previousVertex.duplicate(vertices.size());//NEW
                duplicateVertex.setTextureIndex(newTextureIndex);
                duplicateVertex.setNormalIndex(newNormalIndex);
                previousVertex.setDuplicateVertex(duplicateVertex);
                vertices.add(duplicateVertex);
                indices.add(duplicateVertex.getIndex());
                return duplicateVertex;
            }
        }
    }

    private static void removeUnusedVertices(List<VertexNM> vertices) {
        for (VertexNM vertex : vertices) {
            vertex.averageTangents();
            if (!vertex.isSet()) {
                vertex.setTextureIndex(0);
                vertex.setNormalIndex(0);
            }
        }
    }
}
