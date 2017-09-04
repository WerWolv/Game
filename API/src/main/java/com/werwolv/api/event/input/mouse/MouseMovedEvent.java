package com.werwolv.api.event.input.mouse;

import com.werwolv.api.event.Event;
import com.werwolv.engine.renderer.Camera;

public class MouseMovedEvent extends Event {

    private MouseCoords coords;

    public MouseMovedEvent(double x, double y, Camera camera) {
        super();

        coords = new MouseCoords(x, y, camera);
    }

    public MouseCoords getCoords() {
        return coords;
    }
}
