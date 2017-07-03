package com.werwolv.gui;

import com.werwolv.inventory.Inventory;
import com.werwolv.inventory.slot.Slot;

public class InventoryTest extends Inventory {

    public InventoryTest() {
        super();
        this.addInventorySlot(0, new Slot(35, 52));
    }

    @Override
    public int getSize() {
        return 0;
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
