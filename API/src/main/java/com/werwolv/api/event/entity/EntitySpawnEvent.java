package com.werwolv.api.event.entity;

import com.werwolv.api.event.Event;
import com.werwolv.entities.Entity;

public class EntitySpawnEvent extends Event {

    public Entity spawnedEntity;
    public double posX, posY;

}
