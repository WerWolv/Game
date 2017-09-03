package com.werwolv.entities;

import com.werwolv.api.Log;
import com.werwolv.api.modloader.Mod;
import com.werwolv.container.Container;
import com.werwolv.data.PlayerData;
import com.werwolv.gui.Gui;
import com.werwolv.gui.IGuiHandler;
import com.werwolv.item.ItemStack;
import com.werwolv.utils.SizedStack;
import com.werwolv.world.World;
import org.joml.Vector2f;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EntityPlayer extends Entity {

    private final PlayerData playerData = (PlayerData) new PlayerData().deserialize();

    private Stack<Vector2f> prevPositions = new SizedStack<>(2048);

    private int selectedItemIndex;
    private int numInventoryRows = 5;
    public Map<Integer, ItemStack> inventoryPlayer = new HashMap<>();

    private Gui openedGui;
    private Container openedContainer;

    public EntityPlayer(World world, float posX, float posY) {
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

            this.openedGui = guiHandler.getGuiFromID(guiID, this, this.entityWorld, (int) this.getX(), (int) this.getY());
            this.openedContainer = guiHandler.getInventoryFromID(guiID, this, this.entityWorld, (int) this.getX(), (int) this.getY());

            if(this.openedGui == null)
                Log.wtf("GuiHandler", "This mod has no Gui registered under this ID!");

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Gui getOpenedGui() {
        return openedGui;
    }

    public Container getOpenedContainer() { return openedContainer; }

    public void closeGui() {
        if(this.openedGui != null)
            this.openedGui.onGuiClosed();
        if(this.openedContainer != null)
            this.openedContainer.onContainerClosed();

        this.openedGui = null;
        this.openedContainer = null;
    }

    @Override
    public void move(float x, float y) {
        super.move(x, y);
        this.prevPositions.push(new Vector2f(this.getX(), this.getY()));
    }

    public void setSelectedItemIndex(int index) {
        this.selectedItemIndex = index;
    }

    public int getNumInventoryRows() {
        return numInventoryRows;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public Stack<Vector2f> getPrevPositions() {
        return prevPositions;
    }
}
