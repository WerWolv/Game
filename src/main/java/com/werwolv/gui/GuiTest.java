package com.werwolv.gui;

import com.werwolv.api.API;
import com.werwolv.engine.renderer.GuiRenderer;
import com.werwolv.state.State;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class GuiTest extends Gui{

    private int backgroundTexture;

    public GuiTest() {
        backgroundTexture = API.ResourceRegistry.registerResource("game/gui/guiInventory.png");
    }

    @Override
    public void update(long deltaTime) {
    }

    @Override
    public void render(GuiRenderer renderer) {
        renderer.renderGui(API.ResourceRegistry.getTextureFromID(backgroundTexture), State.getCurrentState().getCamera(), new Vector2f(0, 50), new Vector4f(14, 119, 36, 36));
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        API.ResourceRegistry.unloadResource(backgroundTexture);
    }
}
