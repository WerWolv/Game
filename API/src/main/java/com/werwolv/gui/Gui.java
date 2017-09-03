package com.werwolv.gui;

import com.werwolv.api.IUpdatable;
import com.werwolv.engine.renderer.GuiRenderer;

public abstract class Gui implements IUpdatable {

    public Gui() {
        setUpdateable();
    }

    public abstract void render(GuiRenderer renderer);

    public void onGuiClosed() { }

}
