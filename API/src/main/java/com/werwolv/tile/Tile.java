package com.werwolv.tile;

import com.werwolv.api.API;
import com.werwolv.engine.Model;
import com.werwolv.tileEntities.TileEntity;

public class Tile {

    public static final short TILE_SIZE = 32;

    private Model modelTile;

    private final int tileID;
    private String unlocalizedName;
    private int textureID = -1;
    private TileEntity tileEntity;

    public Tile(int tileID) {
        this.tileID = tileID;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
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

    public Tile addTileEntity(Class<? extends TileEntity> tileEntity) {
        try {
            this.tileEntity = tileEntity.newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TileEntity getTileEntity() {
        return this.tileEntity;
    }


    public int getTileID() {
        return tileID;
    }

    public int getTextureID() {
        return textureID;
    }

}
