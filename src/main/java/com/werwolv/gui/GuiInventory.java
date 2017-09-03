package com.werwolv.gui;

import com.werwolv.api.API;
import com.werwolv.engine.renderer.GuiRenderer;

import java.awt.*;

public class GuiInventory extends Gui {

    private int backgroundTexture;

    private final Rectangle[] inventoryRows;


    private int numRows;

    public GuiInventory(int numRows) {
        this.numRows = numRows;
        backgroundTexture = API.ResourceRegistry.registerResource("game/gui/guiInventory.png");

        inventoryRows = new Rectangle[] {
            new Rectangle(0, 0, 246, 58),
            new Rectangle(0, 59, 246, 26),
            new Rectangle(0, 86, 246, 32)
        };
    }

    @Override
    public void update(long deltaTime) {

    }

    @Override
    public void render(GuiRenderer renderer) {
        renderer.drawDefaultBackground();

        int scale = 3;
        int offsetX = inventoryRows[0].width / 2 * scale;
        int offsetY = (inventoryRows[0].height + inventoryRows[1].height * (numRows - 2)) / 2 * scale;

        renderer.drawGuiTexture(backgroundTexture, -offsetX, -offsetY, inventoryRows[0], scale);

        for(int y = 0; y < numRows - 2; y++)
            renderer.drawGuiTexture(backgroundTexture, -offsetX, (inventoryRows[0].height + inventoryRows[1].height * y) * scale - offsetY, inventoryRows[1], scale);

        renderer.drawGuiTexture(backgroundTexture, -offsetX, (inventoryRows[0].height + inventoryRows[1].height * (numRows - 2)) * scale - offsetY, inventoryRows[2], scale);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        API.ResourceRegistry.unloadResource(backgroundTexture);
    }

}
