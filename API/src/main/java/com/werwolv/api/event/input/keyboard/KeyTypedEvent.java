package com.werwolv.api.event.input.keyboard;

import com.werwolv.api.event.Event;

public class KeyTypedEvent extends Event {

    private final int keyCode;

    public KeyTypedEvent(int keyCode) {
        super();
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

}
