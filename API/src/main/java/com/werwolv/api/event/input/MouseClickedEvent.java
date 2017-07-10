package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseClickedEvent extends Event {

    private final EnumMouseButton button;
    private final double x, y;

    public MouseClickedEvent(EnumMouseButton button, double x, double y) {
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public EnumMouseButton getButton() {
        return button;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
