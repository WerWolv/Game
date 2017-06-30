package com.werwolv.entities;

import com.werwolv.api.Log;
import com.werwolv.api.modloader.Mod;
import com.werwolv.gui.Gui;
import com.werwolv.gui.IGuiHandler;
import com.werwolv.world.World;

public class EntityPlayer extends Entity {

    private Gui openedGui = null;

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

            if(guiHandler == null) {
                Log.wtf("GuiHandler", "This mod has no GuiHandler registered to it!");
                return;
            }

            this.openedGui = guiHandler.getGuiFromID(guiID, this, this.entityWorld, (int) this.getPosX(), (int) this.getPosY());

            if(this.openedGui == null)
                Log.wtf("GuiHandler", "This mod has no Gui registered under this ID!");

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Gui getOpenedGui() {
        return openedGui;
    }
}
