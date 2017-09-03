package com.werwolv.api.event.input;

import com.werwolv.api.API;
import com.werwolv.engine.renderer.Camera;

public class MouseCoords {

    private final double x, y;
    private final Camera camera;

    public MouseCoords(double x, double y, Camera camera) {
        this.x = x;
        this.y = y;
        this.camera = camera;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWorldSpaceX() {
        return camera.getX() + ((int)x - API.ContextValues.WINDOW_WIDTH / 2) / API.ContextValues.WORLD_SCALE;
    }

    public double getWorldSpaceY() {
        return -(camera.getY() + ((int) y - API.ContextValues.WINDOW_HEIGHT / 2) / API.ContextValues.WORLD_SCALE);
    }
}
