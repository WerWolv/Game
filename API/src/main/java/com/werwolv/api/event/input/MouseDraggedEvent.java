package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseDraggedEvent extends Event {

    private final EnumMouseButton button;
    private final int x, y;

    public MouseDraggedEvent(EnumMouseButton button, int x, int y) {
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public EnumMouseButton getButton() {
        return button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
