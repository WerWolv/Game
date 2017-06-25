package com.werwolv.handler;

import com.werwolv.api.event.input.KeyPressedEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onKeyPressed(KeyPressedEvent event) {
        System.out.println("Derp");
    }

}
