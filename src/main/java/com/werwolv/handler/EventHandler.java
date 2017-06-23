package com.werwolv.handler;

import com.werwolv.api.event.EventBusSubscriber;
import com.werwolv.api.event.KeyEvent;
import com.werwolv.api.event.SubscribeEvent;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onKeyPressed(KeyEvent.KeyPressed event) {

    }

}
