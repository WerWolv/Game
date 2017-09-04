package com.test.mod;

import com.werwolv.api.event.input.keyboard.KeyPressedEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;

@EventBusSubscriber
public class EventHandlerTest {

    @SubscribeEvent
    public void onKeyPressed(KeyPressedEvent e) {

    }
}
