package com.werwolv.engine.renderer;

import com.werwolv.api.API;
import com.werwolv.api.resource.Texture;
import com.werwolv.engine.Model;
import com.werwolv.engine.Shader;
import com.werwolv.main.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class TileRenderer {

    public Shader shader = new Shader("shader");
    public Model tileModel;
    public Texture texture;
    private float[] vertices = new float[] { -0.5F,  0.5F, 0, 0.5F,  0.5F, 0, 0.5F, -0.5F, 0, -0.5F, -0.5F, 0 };
    private float[] textureCooords = new float[] { 0, 0, 1, 0, 1, 1, 0, 1 };
    private int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

    public TileRenderer() {
        tileModel = new Model(vertices, textureCooords, indices);
    }

    public void renderTile(int tileId, float x, float y, Matrix4f worldSpaceMatrix, Camera camera) {
        texture = API.ResourceRegistry.getResourceFromID(API.GameRegistry.getTileFromID(tileId).getTextureID());
        shader.bind();

        texture.bind(0);

        Matrix4f tilePosition = new Matrix4f().translate(new Vector3f(x + 0.5F - camera.getX(), y + 0.5F - camera.getY(), 0.0F));
        Matrix4f target = new Matrix4f();

        camera.getProjection().mul(worldSpaceMatrix, target);
        target.mul(tilePosition);

        shader.setUniform("sampler", 0);
        shader.setUniform("projMatrix", target);
        shader.setUniform("color", new Vector3f(-1, -1, -1));

        tileModel.render();

        shader.unbind();
        texture.unbind();
    }

    public void renderColor(Vector3f color, float x, float y, Matrix4f worldSpaceMatrix, Camera camera) {
        shader.bind();

        Matrix4f tilePosition = new Matrix4f().translate(new Vector3f(x + 0.5F - camera.getX(), y + 0.5F - camera.getY(), 0.0F));
        Matrix4f target = new Matrix4f();

        camera.getProjection().mul(worldSpaceMatrix, target);
        target.mul(tilePosition);

        shader.setUniform("sampler", 0);
        shader.setUniform("projMatrix", target);
        shader.setUniform("color", color);

        tileModel.render();

        shader.unbind();
    }

}
