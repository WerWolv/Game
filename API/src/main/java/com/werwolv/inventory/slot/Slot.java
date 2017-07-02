package com.werwolv.inventory.slot;

import com.werwolv.item.ItemStack;

public abstract class Slot {

    private final int posX, posY;

    private ItemStack itemStack;

    private int itemStackX, itemStackY;

    public Slot(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack, int itemStackX, int itemStackY) {
        this.itemStack = itemStack;

        this.itemStackX = itemStackX;
        this.itemStackY = itemStackY;
    }

    public int getItemStackX() {
        return itemStackX;
    }

    public int getItemStackY() {
        return itemStackY;
    }
}
