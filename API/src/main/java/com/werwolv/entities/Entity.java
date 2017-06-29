package com.werwolv.entities;

import com.werwolv.api.IUpdatable;
import com.werwolv.world.World;

public abstract class Entity implements IUpdatable {

    private double health = 100D;
    private double maxHealth = 100D;

    private double posX, posY;
    private boolean isDead = false;

    private World entityWorld;

    public Entity(World world) {
        this.posX = 0;
        this.posY = 0;
        this.entityWorld = world;

        setUpdateable();
    }

    public Entity(World world, double posX, double posY) {
        this(world);

        this.posX = posX;
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void move(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

}
