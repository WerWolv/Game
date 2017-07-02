package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseClickedEvent extends Event {

    private final EnumMouseButton button;
    private final int x, y;
    private final int clickCnt;

    public MouseClickedEvent(EnumMouseButton button, int x, int y, int clickCnt) {
        this.button = button;
        this.x = x;
        this.y = y;
        this.clickCnt = clickCnt;
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

    public int getClickCnt() {
        return clickCnt;
    }
}
