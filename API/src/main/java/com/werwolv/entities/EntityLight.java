package com.werwolv.entities;

import com.werwolv.world.World;
import org.joml.Vector3f;

public class EntityLight extends Entity {

    private Vector3f lightColor;

    public EntityLight(Vector3f lightColor, World world, float posX, float posY) {
        super(world, posX, posY);

        this.lightColor = lightColor;
    }

    @Override
    public void update(long deltaTime) {

    }


}
