package com.werwolv.engine.renderer;

import com.werwolv.api.API;
import com.werwolv.engine.resource.Texture;
import com.werwolv.engine.Model;
import com.werwolv.engine.Shader;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class GuiRenderer {

    public Shader shader = new Shader("game:gui");

    private float[] vertices = new float[] { -0.5F,  0.5F, 0, 0.5F,  0.5F, 0, 0.5F, -0.5F, 0, -0.5F, -0.5F, 0 };
    private float[] textureCooords = new float[] { 0, 0, 1, 0, 1, 1, 0, 1 };
    private int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

    private Model model = new Model(vertices, textureCooords, indices);

    public GuiRenderer() {

    }

    public void renderGui(Texture texture, Camera camera, Vector2f position, Vector4f drawRegion) {
        shader.bind();


        texture.bind(0);

        Matrix4f target = new Matrix4f();
        camera.getProjection().mul(new Matrix4f().scale(Math.min(API.ContextValues.WINDOW_HEIGHT, API.ContextValues.WINDOW_WIDTH)), target);

        position.set(position.x / texture.getWidth(), position.y / texture.getHeight());

        shader.setUniform("sampler", 0);
        shader.setUniform("projMatrix", target);
        shader.setUniform("drawPosition", position);
        shader.setUniform("drawRegion", drawRegion.add(new Vector4f(0, 0, drawRegion.x, drawRegion.y)).div(new Vector4f(texture.getWidth(), texture.getHeight(), texture.getWidth(), texture.getHeight())));


        model.render();

        shader.unbind();
        texture.unbind();
    }
}
