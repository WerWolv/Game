package com.werwolv.entities;

import com.werwolv.api.IUpdatable;
import com.werwolv.world.AABB;
import com.werwolv.world.World;
import org.joml.Vector2f;

public abstract class Entity implements IUpdatable {

    protected double health = 100D;
    protected double maxHealth = 100D;

    private float posX, posY;
    private boolean isDead = false;

    protected World entityWorld;

    public Entity(World world) {
        this.posX = 0;
        this.posY = 0;
        this.entityWorld = world;

        setUpdateable();
    }

    public Entity(World world, float posX, float posY) {
        this(world);

        this.posX = posX;
        this.posY = posY;
    }

    public float getX() {
        return posX;
    }

    public void setX(float posX) {
        this.posX = posX;
    }

    public float getY() {
        return posY;
    }

    public void setY(float posY) {
        this.posY = posY;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void move(float x, float y) {
        this.posX += x;
        this.posY += y;
    }

    public AABB getBoundingBox() {
        return new AABB(new Vector2f(0.5F, 0.5F), new Vector2f(0.5F, 0.5F));
    }

}
