package com.werwolv.entities;

import com.werwolv.api.Log;
import com.werwolv.api.modloader.Mod;
import com.werwolv.container.Container;
import com.werwolv.gui.Gui;
import com.werwolv.gui.IGuiHandler;
import com.werwolv.container.ContainerPlayer;
import com.werwolv.item.ItemStack;
import com.werwolv.world.World;

import java.util.HashMap;
import java.util.Map;

public class EntityPlayer extends Entity {

    private int selectedItemIndex;
    private int numInventoryRows = 5;
    public Map<Integer, ItemStack> inventoryPlayer = new HashMap<>();

    private Gui openedGui;
    private Container openendInventory;

    public EntityPlayer(World world, double posX, double posY) {
        super(world, posX, posY);
    }

    @Override
    public void update(long deltaTime) {

    }

    public void openGui(Class<?> modClass, int guiID) {
        try {

            if(!modClass.isAnnotationPresent(Mod.class)) {
                Log.wtf("GuiHandler", "Passed class is no mod base class!");
                return;
            }

            Mod mod = modClass.getAnnotation(Mod.class);
            IGuiHandler guiHandler = mod.guiHandler().newInstance();

            this.openedGui = guiHandler.getGuiFromID(guiID, this, this.entityWorld, (int) this.getPosX(), (int) this.getPosY());
            this.openendInventory = guiHandler.getInventoryFromID(guiID, this, this.entityWorld, (int) this.getPosX(), (int) this.getPosY());

            if(this.openedGui == null)
                Log.wtf("GuiHandler", "This mod has no Gui registered under this ID!");

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Gui getOpenedGui() {
        return openedGui;
    }

    public Container getOpenendInventory() { return openendInventory; }

    public void closeGui() {
        if(this.openedGui != null)
            this.openedGui.onGuiClosed();

        this.openedGui = null;
        this.openendInventory = null;
    }

    public void setSelectedItemIndex(int index) {
        this.selectedItemIndex = index;
    }

    public int getNumInventoryRows() {
        return numInventoryRows;
    }
}
