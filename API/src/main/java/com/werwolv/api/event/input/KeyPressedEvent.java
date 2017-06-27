package com.werwolv.api.event.input;

import com.werwolv.api.event.Event;

public class KeyPressedEvent extends Event {

    private int keyCode;

    public KeyPressedEvent(int keyCode) {
        super();
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

}
