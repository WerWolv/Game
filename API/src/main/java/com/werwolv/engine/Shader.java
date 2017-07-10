package com.werwolv.engine;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private int program;
    private int vertId;
    private int fragId;

    private Map<String, Integer> cacheLocations = new HashMap<>();

    public Shader(String shaderPath) {
        program = glCreateProgram();

        vertId = glCreateShader(GL_VERTEX_SHADER);
        fragId = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertId, readFile(shaderPath + ".vert"));
        glShaderSource(fragId, readFile(shaderPath + ".frag"));

        glCompileShader(vertId);
        glCompileShader(fragId);

        if(glGetShaderi(vertId, GL_COMPILE_STATUS) != GL_TRUE)
            System.err.println(glGetShaderInfoLog(vertId));

        if(glGetShaderi(fragId, GL_COMPILE_STATUS) != GL_TRUE)
            System.err.println(glGetShaderInfoLog(fragId));

        glAttachShader(program, vertId);
        glAttachShader(program, fragId);

        glLinkProgram(program);

        if(glGetProgrami(program, GL_LINK_STATUS) != GL_TRUE)
            System.err.println(glGetProgramInfoLog(program));

        glValidateProgram(program);

        if(glGetProgrami(program, GL_VALIDATE_STATUS) != GL_TRUE)
            System.err.println(glGetProgramInfoLog(program));
    }

    public void bind() {
        glUseProgram(program);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void setUniform(String uniformName, int value) {
        int location = glGetUniformLocation(program, uniformName);
        if (location != -1) glUniform1i(location, value);
    }

    public void setUniform(String uniformName, Vector4f value) {
        int location = glGetUniformLocation(program, uniformName);
        if (location != -1) glUniform4f(location, value.x, value.y, value.z, value.w);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        int location = glGetUniformLocation(program, uniformName);
        float[] data = new float[16];
        value.get(data);
        if (location != -1) glUniformMatrix4fv(location, false, data);
    }

    private String readFile(String path) {
        StringBuilder text = new StringBuilder();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("shaders/" + path));

            String line;
            while((line = reader.readLine()) != null)
                text.append(line + '\n');

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

}
