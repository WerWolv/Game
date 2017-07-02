package com.werwolv.inventory;

import com.werwolv.inventory.slot.Slot;
import com.werwolv.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Inventory {

    private Map<Integer, Slot> inventorySlots = new HashMap<>();

    public abstract int getSize();

    public abstract String getInventoryName();

    public abstract int getMaxStackSize();

    public ItemStack getStackInSlot(int index) {
        Slot slot = inventorySlots.get(index);

        return slot == null ? null : slot.getItemStack();
    }

    public ItemStack decrStackSize(int index, int amount) {

        ItemStack itemStack = getStackInSlot(index);
        itemStack.setStackSize(itemStack.getStackSize() - amount);

        Slot slot = inventorySlots.get(index);

        if(itemStack.getStackSize() == 0)
            this.setInventorySlotContent(index, null, 0,0);
        else
            this.setInventorySlotContent(index, itemStack, slot.getItemStackX(), slot.getItemStackY());

        return getStackInSlot(index);
    }

    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = inventorySlots.get(index).getItemStack();

        inventorySlots.get(index).setItemStack(null, 0, 0);

        return itemStack;
    }

    public void setInventorySlotContent(int index, ItemStack itemStack, int itemStackPosX, int itemStackPosY) {
        this.inventorySlots.get(index).setItemStack(itemStack, itemStackPosX, itemStackPosY);
    }
}
