package com.werwolv.inventory.slot;

import com.werwolv.item.ItemStack;

import java.awt.*;

public class Slot {

    public static final Color SLOT_COLOR = new Color(0xFF, 0xFF, 0xFF, 0x7F);

    private final int posX, posY;
    private final int size;

    private ItemStack itemStack;

    public Slot(int posX, int posY, int size) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
