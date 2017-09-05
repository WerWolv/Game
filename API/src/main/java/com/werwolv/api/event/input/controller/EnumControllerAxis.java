package com.werwolv.api.event.input.controller;

public enum EnumControllerAxis {
    STICK_LEFT_X(0),
    STICK_LEFT_Y(1),
    STICK_RIGHT_X(2),
    STICK_RIGHT_Y(5),
    TRIGGER_LEFT(3),
    TRIGGER_RIGHT(4);

    private int id;

    EnumControllerAxis(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
