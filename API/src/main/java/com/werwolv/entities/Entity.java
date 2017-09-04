package com.werwolv.entities;

import com.werwolv.api.IUpdatable;
import com.werwolv.world.AABB;
import com.werwolv.world.World;
import org.joml.Vector2f;

public abstract class Entity implements IUpdatable {

    protected double health = 100D;
    protected double maxHealth = 100D;

    protected double maxSpeed = 0.1D;

    private float posX, posY;
    private float nextX, nextY;
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

    @Override
    public void update(long deltaTime) {
        this.posX += nextX;
        this.posY += nextY;

        this.nextX = 0;
        this.nextY = 0;
    }

    public float getX() {
        return posX;
    }

    public void setX(float posX) {
        this.nextX = posX;
    }

    public float getY() {
        return posY;
    }

    public void setY(float posY) {
        this.nextY = posY;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void move(float x, float y) {
        this.nextX += x;
        this.nextY += y;

        this.nextX = (float) Math.max(-maxSpeed, Math.min(maxSpeed, nextX));
        this.nextY = (float) Math.max(-maxSpeed, Math.min(maxSpeed, nextY));

    }

    public AABB getBoundingBox() {
        return new AABB(new Vector2f(0.5F, 0.5F), new Vector2f(0.5F, 0.5F));
    }

}
