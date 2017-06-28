package com.werwolv.api.capabilities;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public interface ICapabilityProvider {

    boolean hasCapability(@NotNull Capability<?> capability);

    @Nullable
    <T> T getCapability(@NotNull Capability<T> capability);

}
