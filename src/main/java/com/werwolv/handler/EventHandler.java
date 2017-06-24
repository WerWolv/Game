package com.werwolv.handler;

import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.event.KeyEvent;
import com.werwolv.api.eventbus.SubscribeEvent;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onKeyPressed(KeyEvent.KeyPressed event) {
        System.out.println("Derp");
    }

}
