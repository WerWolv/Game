package com.werwolv.item;

import com.werwolv.api.API;
import com.werwolv.api.Log;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.world.World;

public class Item {

    private final int itemID;
    private int textureID;
    private String unlocalizedName = "null";

    private int itemDamage, maxItemDamage;

    public Item(int itemID) {
        this.itemID = itemID;
    }

    public Item setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;

        return this;
    }

    public Item setTexture(String path) {
        String[] tokens = path.split(":");

        if(tokens.length != 2) {
            Log.wtf("Item", "Texture path wasn't formatted correctly. Use 'modname:path/to/texture'!");
            return this;
        }

        this.textureID = API.ResourceRegistry.registerResource(tokens[0] + "/items/" + tokens[1] + ".png");

        return this;
    }

    public ItemStack onItemUse(ItemStack itemStack, World world, EntityPlayer entityPlayer, int mouseButton) {
        return itemStack;
    }

    public int getItemID() {
        return itemID;
    }

    public int getTextureID() {
        return textureID;
    }

    public double getItemDamagePrecentage() {
        return this.itemDamage / this.maxItemDamage;
    }

    public void damageItem(int amount) {
        this.itemDamage -= amount;
    }

    public void setItemDamage(int itemDamage) {
        this.itemDamage = itemDamage;
    }

    public void setMaxItemDamage(int maxItemDamage) {
        this.maxItemDamage = maxItemDamage;
    }

    public boolean isDamagable() {
        return false;
    }
}
