package com.werwolv.gui;

import com.werwolv.api.IUpdatable;

import java.awt.Graphics2D;

public abstract class Gui implements IUpdatable {

    public Gui() {
        setUpdateable();
    }

    public abstract void render(Graphics2D g);

}
