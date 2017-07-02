package com.werwolv.gui;

import java.awt.*;

public class GuiTest extends Gui{

    @Override
    public void update(long deltaTime) {
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawRect(50, 50, 100, 100);
    }
}
