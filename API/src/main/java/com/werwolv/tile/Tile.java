package com.werwolv.tile;

import com.werwolv.api.API;

public class Tile {

    public static final short TILE_SIZE = 32;

    private final int tileID;
    private String unlocalizedName = "null";
    private int textureID = -1;

    public Tile(int tileID) {
        this.tileID = tileID;
    }

    public Tile setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;

        return this;
    }

    public Tile setTexture(String path) {
        String[] tokens = path.split(":");
        this.textureID = API.ResourceRegistry.registerResource(tokens[0] + "/tiles/" + tokens[1] + ".png");

        return this;
    }

    public int getTileID() {
        return tileID;
    }

    public int getTextureID() {
        return textureID;
    }

}
