package com.werwolv.tile;

import com.werwolv.tileEntities.TileEntity;
import com.werwolv.tileEntities.TileEntityTest;

public class TileTest extends Tile {

    public TileTest(int tileID) {
        super(tileID);
    }

    @Override
    public TileEntity getTileEntity() {
        return new TileEntityTest();
    }
}
