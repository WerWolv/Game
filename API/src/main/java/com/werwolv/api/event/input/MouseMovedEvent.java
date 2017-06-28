package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseMovedEvent extends Event {

    private int x, y;

    public MouseMovedEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
