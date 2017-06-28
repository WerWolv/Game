package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class MouseClickedEvent extends Event {

    private int button;
    private int x, y;
    private int clickCnt;

    public MouseClickedEvent(int button, int x, int y, int clickCnt) {
        this.button = button;
        this.x = x;
        this.y = y;
        this.clickCnt = clickCnt;
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

    public int getClickCnt() {
        return clickCnt;
    }
}
