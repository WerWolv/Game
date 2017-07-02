package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseWheelScrollEvent extends Event {

    private final int scrollDirection;
    private final int x, y;

    public MouseWheelScrollEvent(int scrollDirection, int x, int y) {
        this.scrollDirection = scrollDirection;
        this.x = x;
        this.y = y;
    }

    public int getScrollDirection() {
        return scrollDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
