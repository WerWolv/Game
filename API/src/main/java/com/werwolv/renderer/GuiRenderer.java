package com.werwolv.renderer;

import com.werwolv.api.API;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GuiRenderer {

    private Graphics2D graphics2D;

    private static final Color DEFAULT_BACKGROUND = new Color(0x00, 0x00, 0x00, 0x7F);

    public interface IRenderer{
        void render(Graphics2D g);
    }

    public void setGraphics(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public void drawDefaultBackground() {
        graphics2D.setColor(DEFAULT_BACKGROUND);
        graphics2D.fillRect(0, 0, API.ContextValues.WINDOW_WIDTH, API.ContextValues.WINDOW_HEIGHT);
    }

    public void drawGuiBackground(int textureID, int offsetX, int offsetY, int width, int height) {
        BufferedImage texture = API.ResourceRegistry.getResourceFromID(textureID);

        graphics2D.drawImage(texture, API.ContextValues.WINDOW_WIDTH / 2 - texture.getWidth() / 2 + offsetX, API.ContextValues.WINDOW_HEIGHT / 2 - texture.getHeight() / 2 + offsetY, width == 0 ? texture.getWidth() : width, height == 0 ? texture.getHeight() : height, null);
    }

    //TODO: Function to only draw a section of a BufferedImage

    public void render(IRenderer renderer) {
        renderer.render(this.graphics2D);
    }

}
