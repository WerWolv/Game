package com.werwolv.gui;

import com.werwolv.api.API;
import com.werwolv.engine.renderer.GuiRenderer;

public class GuiTest extends Gui{

    private int backgroundTexture;

    public GuiTest() {
        backgroundTexture = API.ResourceRegistry.registerResource("game/gui/guiTest.png");
    }

    @Override
    public void update(long deltaTime) {
    }

    @Override
    public void render(GuiRenderer renderer) {
        renderer.drawDefaultBackground();
        renderer.drawGuiBackground(backgroundTexture, 0, 0, 1.0D);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        API.ResourceRegistry.unloadResource(backgroundTexture);
    }
}
