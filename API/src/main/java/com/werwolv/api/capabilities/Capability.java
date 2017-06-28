package com.werwolv.api.capabilities;

public class Capability<T> {

    private String capabilityName;
    private T value;

    public Capability(String capabilityName, T value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }
}
