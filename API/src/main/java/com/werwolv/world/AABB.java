package com.werwolv.world;

import com.werwolv.entities.Entity;
import org.joml.Vector2f;

public class AABB {

    private Vector2f center;
    private Vector2f extent;

    public AABB(Vector2f center, Vector2f extent) {
        this.center = center;
        this.extent = extent;
    }

    public boolean intersects(float x, float y, Entity entity) {
        AABB other = entity.getBoundingBox();

        this.center.add(x, y);
        entity.getBoundingBox().center.add(entity.getX(), entity.getY());

        Vector2f distance = other.center.sub(this.center, new Vector2f());

        distance.x = Math.abs(distance.x);
        distance.y = Math.abs(distance.y);

        distance.sub(extent.add(other.extent, new Vector2f()));

        return distance.x < 0 && distance.y < 0;
    }

}
