package com.werwolv.item;

import java.security.MessageDigest;

public class ItemStack {

    private final Item item;
    private int stackSize;
    private final int metaData;

    private final int horizontalSize, verticalSize;

    public ItemStack(Item item) {
        this.item = item;
        this.stackSize = 1;
        this.metaData = 0;
        this.horizontalSize = this.verticalSize = 1;
    }

    public ItemStack(Item item, int stackSize) {
        this.item = item;
        this.stackSize = stackSize;
        this.metaData = 0;
        this.horizontalSize = this.verticalSize = 1;
    }

    public ItemStack(Item item, int stackSize, int metaData) {
        this.item = item;
        this.stackSize = stackSize;
        this.metaData = metaData;
        this.horizontalSize = this.verticalSize = 1;
    }

    public ItemStack(Item item, int stackSize, int metaData, int horizontalSize, int verticalSize) {
        this.item = item;
        this.stackSize = stackSize;
        this.metaData = metaData;
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;


    }

    public Item getItem() {
        return item;
    }

    public int getMetaData() {
        return metaData;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = Math.max(0, stackSize);
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }
}
