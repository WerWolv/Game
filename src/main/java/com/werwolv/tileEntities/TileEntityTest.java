package com.werwolv.tileEntities;

import com.werwolv.api.event.input.EnumMouseButton;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.main.ModMain;
import com.werwolv.world.World;

public class TileEntityTest extends TileEntity {

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public void onTileClicked(EnumMouseButton button, EntityPlayer player, World world) {
        super.onTileClicked(button, player, world);

        player.openGui(ModMain.class, 0);
    }
}
