package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseDraggedEvent extends Event {

    private int button;
    private int x, y;

    public MouseDraggedEvent(int button, int x, int y) {
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
