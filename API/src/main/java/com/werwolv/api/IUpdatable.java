package com.werwolv.api;

import java.util.ArrayList;
import java.util.List;

public interface IUpdatable {

    List<IUpdatable> updateableInstances = new ArrayList<>();

    void update();

    default void setUpdateable() {
        updateableInstances.add(this);
    }

}
