package com.werwolv.handler;

import com.werwolv.api.API;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean[] pressedKeys = new boolean[1024];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = true;
        API.EVENT_BUS.postEvent(new com.werwolv.api.event.KeyEvent.KeyPressed(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;

        API.EVENT_BUS.postEvent(new com.werwolv.api.event.KeyEvent.KeyReleased(e.getKeyCode()));
    }
}
