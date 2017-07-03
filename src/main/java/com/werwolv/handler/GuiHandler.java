package com.werwolv.handler;

import com.werwolv.entities.EntityPlayer;
import com.werwolv.gui.*;
import com.werwolv.inventory.Inventory;
import com.werwolv.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Gui getGuiFromID(int ID, EntityPlayer entityPlayer, World world, int x, int y) {
        switch(ID) {
            case 0: return new GuiInventory();
            case 1: return new GuiTest();
        }

        return null;
    }

    @Override
    public Inventory getInventoryFromID(int ID, EntityPlayer entityPlayer, World world, int x, int y) {
        switch(ID) {
            case 0: return entityPlayer.inventoryPlayer;
            case 1: return new InventoryTest();
        }

        return null;
    }
}
