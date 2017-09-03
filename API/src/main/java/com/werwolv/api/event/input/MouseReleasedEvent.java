package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;
import com.werwolv.engine.renderer.Camera;

public class MouseReleasedEvent extends Event {

    private final EnumMouseButton button;
    private final MouseCoords coords;

    public MouseReleasedEvent(EnumMouseButton button, double x, double y, Camera camera) {
        this.button = button;

        this.coords = new MouseCoords(x, y, camera);
    }

    public EnumMouseButton getButton() {
        return button;
    }

    public MouseCoords getCoords() {
        return coords;
    }
}
