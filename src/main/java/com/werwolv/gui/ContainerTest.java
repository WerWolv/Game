package com.werwolv.gui;

import com.werwolv.container.Container;
import com.werwolv.inventory.slot.Slot;

public class ContainerTest extends Container {

    public ContainerTest() {
        super();
        this.addInventorySlot(0, new Slot(35, 52, 32));
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
