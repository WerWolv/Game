package com.werwolv.tileEntities;

import com.werwolv.api.IUpdatable;

public abstract class TileEntity implements IUpdatable {

    public TileEntity() {
        this.setUpdateable();
    }

    public void onTileClicked(int button) {

    }
}
