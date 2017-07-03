package com.werwolv.container;

import com.werwolv.entities.EntityPlayer;
import com.werwolv.inventory.slot.Slot;

public class ContainerPlayer extends Container {

    private final EntityPlayer player;

    public ContainerPlayer(EntityPlayer player) {
        this.player = player;

        for(int x = 0; x < 9; x++)
            for(int y = 0; y < player.getNumInventoryRows(); y++)
                this.addInventorySlot(y * 9 + x, new Slot(-312 + x * 78, -70 + y * 78, 78));

        for(int i = 0; i < this.inventorySlots.size(); i++)
            this.inventorySlots.get(i).setItemStack(player.inventoryPlayer.get(i));

    }

    @Override
    public int getSize() {
        return 8 * player.getNumInventoryRows();
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }
}
