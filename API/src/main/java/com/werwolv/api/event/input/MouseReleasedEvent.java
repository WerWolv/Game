package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseReleasedEvent extends Event {

    public final EnumMouseButton button;
    public final double x, y;

    public MouseReleasedEvent(EnumMouseButton button, double x, double y) {
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
