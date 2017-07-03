package com.werwolv.handler;

import com.werwolv.container.Container;
import com.werwolv.container.ContainerPlayer;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.gui.*;
import com.werwolv.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Gui getGuiFromID(int ID, EntityPlayer entityPlayer, World world, int x, int y) {
        switch(ID) {
            case 0: return new GuiInventory(entityPlayer.getNumInventoryRows());
            case 1: return new GuiTest();
        }

        return null;
    }

    @Override
    public Container getInventoryFromID(int ID, EntityPlayer entityPlayer, World world, int x, int y) {
        switch(ID) {
            case 0: return new ContainerPlayer(entityPlayer);
            case 1: return new ContainerTest();
        }

        return null;
    }
}
