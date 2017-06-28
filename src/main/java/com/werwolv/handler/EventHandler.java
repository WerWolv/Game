package com.werwolv.handler;

import com.sun.glass.events.KeyEvent;
import com.werwolv.api.event.input.KeyPressedEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.main.Game;
import com.werwolv.main.Window;
import com.werwolv.states.GameState;
import com.werwolv.states.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onKeyPressed(KeyPressedEvent event) {

    }

    @SubscribeEvent
    public void movement(KeyPressedEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_S) {
            GameState.changecamera(0,-5);
        }
        if(event.getKeyCode() == KeyEvent.VK_W) {
            GameState.changecamera(0,5);
        }
        if(event.getKeyCode() == KeyEvent.VK_A) {
            GameState.changecamera(5,0);
        }
        if(event.getKeyCode() == KeyEvent.VK_D) {
            GameState.changecamera(-5,0);
        }
    }

}
