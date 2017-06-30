package com.werwolv.handler;

import com.werwolv.entities.Entity;
import com.werwolv.gui.Gui;
import com.werwolv.gui.GuiTest;
import com.werwolv.gui.IGuiHandler;
import com.werwolv.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Gui getGuiFromID(int ID, Entity entity, World world, int x, int y) {

        switch(ID) {
            case 0: return new GuiTest();
        }

        return null;
    }
}
