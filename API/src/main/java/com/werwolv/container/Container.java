package com.werwolv.container;

import com.werwolv.inventory.slot.Slot;
import com.werwolv.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Container {

    protected Map<Integer, Slot> inventorySlots = new HashMap<>();
    public ItemStack draggingItem = null;
    private boolean keepsInventory = false;

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
            this.setInventorySlotContent(index, null);

        return getStackInSlot(index);
    }

    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = inventorySlots.get(index).getItemStack();

        inventorySlots.get(index).setItemStack(null);

        return itemStack;
    }

    public void setInventorySlotContent(int index, ItemStack itemStack) {
        this.inventorySlots.get(index).setItemStack(itemStack);
    }

    public Map<Integer, Slot> getInventorySlots() {
        return new HashMap<>(inventorySlots);
    }

    public void addInventorySlot(int slotID, Slot slot) {
        this.inventorySlots.put(slotID, slot);
    }

    public void onContainerClosed() { }
}
