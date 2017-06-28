package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseReleasedEvent extends Event {

    public int button;
    public int x, y;

    public MouseReleasedEvent(int button, int x, int y) {
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public int getButton() {
        return button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
