package com.werwolv.api.event.input.mouse;

import com.werwolv.api.event.Event;
import com.werwolv.engine.renderer.Camera;

public class MousePressedEvent extends Event {

    private final EnumMouseButton button;
    private final MouseCoords coords;

    public MousePressedEvent(EnumMouseButton button, double x, double y, Camera camera) {
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
