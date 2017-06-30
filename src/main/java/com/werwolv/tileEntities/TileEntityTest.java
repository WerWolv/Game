package com.werwolv.tileEntities;

import com.werwolv.entities.EntityPlayer;
import com.werwolv.main.ModMain;
import com.werwolv.world.World;

public class TileEntityTest extends TileEntity {

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public void onTileClicked(int button, EntityPlayer player, World world) {
        super.onTileClicked(button, player, world);

        player.openGui(ModMain.class, 0);

        System.out.println("Clicked TileEntity with " + (button == 1 ? "left" : "right") + " mouse button!");
    }
}
