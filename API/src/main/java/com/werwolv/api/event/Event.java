package com.werwolv.api.event;

public abstract class Event {

    private final long timestamp;

    public Event() {
        this.timestamp = System.currentTimeMillis();
    }

}
