package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class KeyHeldEvent extends Event {

    private final int keyCode;

    public KeyHeldEvent(int keyCode) {
        super();
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

}
