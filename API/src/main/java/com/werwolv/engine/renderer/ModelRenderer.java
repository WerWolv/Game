package com.werwolv.engine.renderer;

import com.werwolv.engine.resource.Texture;
import com.werwolv.engine.Model;
import com.werwolv.engine.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class ModelRenderer {

    public Shader shader = new Shader("game:model");

    private float[] vertices = new float[] { -0.5F,  0.5F, 0, 0.5F,  0.5F, 0, 0.5F, -0.5F, 0, -0.5F, -0.5F, 0 };
    private float[] textureCooords = new float[] { 0, 0, 1, 0, 1, 1, 0, 1 };
    private int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

    Model model1 = new Model(vertices, textureCooords, indices);

    public ModelRenderer() {

    }

    public void renderTile(Model model, Texture texture, float x, float y, Matrix4f worldSpaceMatrix, Camera camera) {
        shader.bind();

        texture.bind(0);

        Matrix4f tilePosition = new Matrix4f().translate(new Vector3f(x - camera.getX(), y - camera.getY(), 0.0F));
        Matrix4f target = new Matrix4f();

        camera.getProjection().mul(worldSpaceMatrix, target);
        target.mul(tilePosition);

        shader.setUniform("sampler", 0);
        shader.setUniform("projMatrix", target);
        shader.setUniform("color", new Vector4f(-1, -1, -1, -1));

        model.render();

        shader.unbind();
        texture.unbind();
    }

    public void renderColor(Model model, Vector4f color, float x, float y, Matrix4f worldSpaceMatrix, Camera camera) {
        shader.bind();

        Matrix4f tilePosition = new Matrix4f().translate(new Vector3f(x - camera.getX(), y - camera.getY(), 0.0F));
        Matrix4f target = new Matrix4f();

        camera.getProjection().mul(worldSpaceMatrix, target);
        target.mul(tilePosition);

        shader.setUniform("sampler", 0);
        shader.setUniform("projMatrix", target);
        shader.setUniform("color", color);

        model.render();

        shader.unbind();
    }

}
