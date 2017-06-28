package com.werwolv.entities;

import com.werwolv.api.capabilities.Capability;
import com.werwolv.api.capabilities.ICapabilityProvider;

import java.util.ArrayList;
import java.util.List;

public class Entity implements ICapabilityProvider {

    private static List<Capability<?>> globalCapabilities = new ArrayList<>();
    private List<Capability<?>> localCapabilities = new ArrayList<>();


    @Override
    public boolean hasCapability(Capability<?> capability) {
        return globalCapabilities.contains(capability) || localCapabilities.contains(capability);
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        return null;
    }
}
