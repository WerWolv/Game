package com.werwolv.engine.renderer;

import com.werwolv.api.API;

import java.awt.*;

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

    public void drawGuiBackground(int textureID, int offsetX, int offsetY, double scale) {
       /* BufferedImage texture = API.ResourceRegistry.getResourceFromID(textureID);

        graphics2D.drawImage(texture, API.ContextValues.WINDOW_WIDTH / 2 - texture.getWidth() / 2 + offsetX, API.ContextValues.WINDOW_HEIGHT / 2 - texture.getHeight() / 2 + offsetY, (int)(texture.getWidth() * scale), (int)(texture.getHeight() * scale), null);
    */}

    public void drawGuiTexture(int textureID, int offsetX, int offsetY, double scale) {
       /* BufferedImage texture = API.ResourceRegistry.getResourceFromID(textureID);

        graphics2D.drawImage(texture, API.ContextValues.WINDOW_WIDTH / 2 + offsetX, API.ContextValues.WINDOW_HEIGHT / 2 + offsetY, (int)(texture.getWidth() * scale), (int)(texture.getHeight() * scale), null);
    */}

    public void drawGuiTexture(int textureID, int offsetX, int offsetY, int sectorX1, int sectorY1, int sectorX2, int sectorY2, double scale) {
   /*     BufferedImage texture = API.ResourceRegistry.getResourceFromID(textureID).getSubimage(sectorX1, sectorY1, sectorX2, sectorY2);

        graphics2D.drawImage(texture, API.ContextValues.WINDOW_WIDTH / 2 + offsetX, API.ContextValues.WINDOW_HEIGHT / 2 + offsetY, (int)(texture.getWidth() * scale), (int)(texture.getHeight() * scale), null);
   */ }

    public void drawGuiTexture(int textureID, int offsetX, int offsetY, Rectangle rect, double scale) {
    /*    BufferedImage texture = API.ResourceRegistry.getResourceFromID(textureID).getSubimage(rect.x, rect.y, rect.width, rect.height);

        graphics2D.drawImage(texture, API.ContextValues.WINDOW_WIDTH / 2 + offsetX, API.ContextValues.WINDOW_HEIGHT / 2 + offsetY, (int)(texture.getWidth() * scale), (int)(texture.getHeight() * scale), null);
    */}

    public void render(IRenderer renderer) {
        renderer.render(this.graphics2D);
    }

}
