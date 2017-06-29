package com.werwolv.world;

public class Chunk<T> {

    public static final int CHUNK_WIDTH = 1;

    private T[][] gridObjects;

    public Chunk(T[][] gridObject) {
        this.gridObjects = gridObject;
    }

    public T[][] getGridObjects() {
        return gridObjects;
    }

    public void setGridObject(T tile, int posX, int posY) {
        this.gridObjects[posX][posY] = tile;
    }

}
