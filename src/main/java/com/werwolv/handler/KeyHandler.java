package com.werwolv.handler;

import com.werwolv.api.API;
import com.werwolv.api.event.input.KeyHeldEvent;
import com.werwolv.api.event.input.KeyPressedEvent;
import com.werwolv.api.event.input.KeyReleasedEvent;
import com.werwolv.api.event.input.KeyTypedEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private static boolean[] pressedKeys = new boolean[1024];

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!pressedKeys[e.getKeyCode()])
            API.EVENT_BUS.postEvent(new KeyTypedEvent(e.getKeyCode()));
        else
            API.EVENT_BUS.postEvent(new KeyHeldEvent(e.getKeyCode()));

        pressedKeys[e.getKeyCode()] = true;
        API.EVENT_BUS.postEvent(new KeyPressedEvent(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;
        API.EVENT_BUS.postEvent(new KeyReleasedEvent(e.getKeyCode()));
    }

    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys[keyCode];
    }

}
