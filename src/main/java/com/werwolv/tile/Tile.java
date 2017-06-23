package com.werwolv.tile;

import com.werwolv.api.API;

public class Tile {

    private final int tileID;
    private String unlocalizedName;
    private int textureID = -1;

    public Tile(int tileID) {
        this.tileID = tileID;
    }

    public Tile setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;

        return this;
    }

    public Tile setTexture(String path) {
        this.textureID = API.ResourceRegistry.registerResource(path);

        return this;
    }

    public int getTileID() {
        return tileID;
    }

    public int getTextureID() {
        return textureID;
    }

}
