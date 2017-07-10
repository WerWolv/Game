package com.werwolv.main;

import com.werwolv.api.API;
import com.werwolv.api.IUpdatable;
import com.werwolv.entities.Entity;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera implements IUpdatable {

    private float x, y;
    private float lerp;
    private Matrix4f projection;

    private Entity entityToFollow;

    public Camera() {
        recreateViewPort();

        this.lerp = 0.0F;
        this.setUpdateable();
    }

    public void setEntityToFollow(Entity entityToFollow) {
        this.entityToFollow = entityToFollow;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getLerp() {
        return lerp;
    }

    public void setLerp(float lerp) {
        this.lerp = lerp;
    }

    public Matrix4f getProjection() {
        Matrix4f target = new Matrix4f();
        Matrix4f pos = new Matrix4f().setTranslation(new Vector3f(x, y, 1.0F));

        target = projection.mul(pos, target);

        return target;
    }

    public void recreateViewPort() {
        int width = API.ContextValues.WINDOW_WIDTH;
        int height = API.ContextValues.WINDOW_HEIGHT;

        projection = new Matrix4f().ortho2D(-width/2, width/2, -height/2, height/2);    }

    @Override
    public void update(long deltaTime) {
        if(entityToFollow != null) {
            this.x += (entityToFollow.getPosX() - this.x) * lerp;
            this.y += (entityToFollow.getPosY() - this.y) * lerp;
        }
    }
}
