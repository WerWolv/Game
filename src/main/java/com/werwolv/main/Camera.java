package com.werwolv.main;

import com.werwolv.api.IUpdatable;
import com.werwolv.entities.Entity;

public class Camera implements IUpdatable {

    private double x, y;
    private float lerp;

    private Entity entityToFollow;

    public Camera() {
        this.x = 0;
        this.y = 0;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public float getLerp() {
        return lerp;
    }

    public void setLerp(float lerp) {
        this.lerp = lerp;
    }

    @Override
    public void update(long deltaTime) {
        if(entityToFollow != null) {
            this.x += (entityToFollow.getPosX() - this.x) * lerp;
            this.y += (entityToFollow.getPosY() - this.y) * lerp;
        }
    }
}
