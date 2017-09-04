package com.werwolv.tileEntities;

import com.werwolv.api.event.input.mouse.EnumMouseButton;
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
        player.openGui(ModMain.class, 0);
    }
}
