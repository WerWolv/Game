package com.werwolv.api.event.input.controller;

import com.werwolv.api.event.Event;

import java.nio.FloatBuffer;

public class ControllerAnalogInputEvent extends Event {

    private FloatBuffer axis;

    public ControllerAnalogInputEvent(FloatBuffer axis) {
        this.axis = axis;
    }

    public float getAxis(EnumControllerAxis axis) {
        float value = Math.max(-1.0F, Math.min(1.0F, this.axis.get(axis.getId())));
        return value > 0.1F || value < -0.1F ? value : 0.0F;
    }
}
