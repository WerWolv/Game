package com.werwolv.tileEntities;

import com.werwolv.api.IUpdatable;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.world.World;

public abstract class TileEntity implements IUpdatable {

    public TileEntity() {
        this.setUpdateable();
    }

    public void onTileClicked(int button, EntityPlayer player, World world) {

    }
}
