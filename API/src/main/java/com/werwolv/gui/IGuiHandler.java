package com.werwolv.gui;

import com.werwolv.entities.Entity;
import com.werwolv.world.World;

public interface IGuiHandler {

    Gui getGuiFromID(int ID, Entity entity, World world, int x, int y);

}
