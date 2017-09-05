package com.werwolv.world;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Chunk<T> {

    public static final int CHUNK_WIDTH = 1;

    private Map<Pair<Integer, Integer>, T> gridObjects = new HashMap<>();

    public Map<Pair<Integer, Integer>, T> getGridObjects() {
        return gridObjects;
    }

    public void setGridObject(T tile, int posX, int posY) {
        this.gridObjects.put(new Pair<>(posX, posY), tile);
    }

}
