package com.werwolv.gui;

import com.werwolv.entities.EntityPlayer;
import com.werwolv.inventory.Inventory;
import com.werwolv.world.World;

public interface IGuiHandler {

    Gui getGuiFromID(int ID, EntityPlayer entityPlayer, World world, int x, int y);

    Inventory getInventoryFromID(int ID, EntityPlayer entityPlayer, World world, int x, int y);

}
