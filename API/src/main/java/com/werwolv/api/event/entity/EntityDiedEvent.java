package com.werwolv.api.event.entity;

import com.werwolv.api.event.Event;
import com.werwolv.entities.Entity;

public class EntityDiedEvent extends Event {

    private final Entity diedEntity;
    private final double deathX, deathY;

    public EntityDiedEvent(Entity diedEntity, double deathX, double deathY) {
        super();
        this.diedEntity = diedEntity;
        this.deathX = deathX;
        this.deathY = deathY;
    }

    public Entity getDiedEntity() {
        return diedEntity;
    }

    public double getDeathX() {
        return deathX;
    }

    public double getDeathY() {
        return deathY;
    }
}
