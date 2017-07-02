package com.werwolv.api.event.entity;

import com.werwolv.api.event.Event;
import com.werwolv.entities.Entity;

public class EntitySpawnEvent extends Event {

    public final Entity spawnedEntity;
    public final double posX, posY;

    public EntitySpawnEvent(Entity spawnedEntity, double posX, double posY) {
        this.spawnedEntity = spawnedEntity;
        this.posX = posX;
        this.posY = posY;
    }

    public Entity getSpawnedEntity() {
        return spawnedEntity;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
