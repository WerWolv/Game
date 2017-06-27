package com.test.mod;

import com.werwolv.api.API;
import com.werwolv.api.event.init.InitializationEvent;
import com.werwolv.api.event.input.KeyPressedEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;

@EventBusSubscriber
public class EventHandlerTest {

    @SubscribeEvent
    public void onKeyPressed(KeyPressedEvent e) {
        System.out.println(e.getKeyCode());
    }
}
