package com.werwolv.tileEntities;

import com.werwolv.api.API;
import com.werwolv.api.event.input.EnumMouseButton;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.main.ModMain;
import com.werwolv.world.World;

public class TileEntityTest extends TileEntity {

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public void onTileClicked(EnumMouseButton button, EntityPlayer player, World world, int x, int y) {
        super.onTileClicked(button, player, world, x, y);
        System.out.println(x + ", " + y);

       // world.setTile(API.TileRegistry.getTileFromID(2), x, y);
    }
}
