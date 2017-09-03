package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;
import com.werwolv.engine.renderer.Camera;

public class MouseWheelScrollEvent extends Event {

    private final int scrollDirection;
    private final MouseCoords coords;

    public MouseWheelScrollEvent(int scrollDirection, double x, double y, Camera camera) {
        this.scrollDirection = scrollDirection;

        this.coords = new MouseCoords(x, y, camera);
    }

    public int getScrollDirection() {
        return scrollDirection;
    }

    public MouseCoords getCoords() {
        return coords;
    }
}
