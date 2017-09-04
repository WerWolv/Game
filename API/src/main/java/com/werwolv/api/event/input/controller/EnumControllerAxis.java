package com.werwolv.api.event.input.controller;

public enum EnumControllerAxis {
    STICK_LEFT_X(0),
    STICK_LEFT_Y(1),
    STICK_RIGHT_X(2),
    STICK_RIGHT_Y(3),
    TRIGGER_LEFT(4),
    TRIGGER_RIGHT(5);

    private int id;

    EnumControllerAxis(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
