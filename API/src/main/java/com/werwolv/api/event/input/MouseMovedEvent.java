package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseMovedEvent extends Event {

    private final double x, y;

    public MouseMovedEvent(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
