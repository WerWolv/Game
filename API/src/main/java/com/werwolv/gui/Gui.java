package com.werwolv.gui;

import com.werwolv.api.IUpdatable;
import com.werwolv.renderer.GuiRenderer;

import java.awt.Graphics2D;

public abstract class Gui implements IUpdatable {

    public Gui() {
        setUpdateable();
    }

    public abstract void render(GuiRenderer renderer);

    public void onGuiClosed() { }

}
