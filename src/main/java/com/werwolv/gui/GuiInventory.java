package com.werwolv.gui;

import com.werwolv.api.API;
import com.werwolv.renderer.GuiRenderer;

public class GuiInventory extends Gui {

    private int backgroundTexture;

    public GuiInventory() {
        backgroundTexture = API.ResourceRegistry.registerResource("game/gui/guiInventory.png");
    }

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public void render(GuiRenderer renderer) {
        renderer.drawDefaultBackground();

        renderer.drawGuiBackground(backgroundTexture, -API.ContextValues.WINDOW_WIDTH / 2 + 128, -API.ContextValues.WINDOW_HEIGHT / 2 + 128, 512, 512);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        API.ResourceRegistry.unloadResource(backgroundTexture);
    }

}
