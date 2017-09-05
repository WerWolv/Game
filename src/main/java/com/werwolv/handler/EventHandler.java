package com.werwolv.handler;

import com.werwolv.api.API;
import com.werwolv.api.event.input.controller.ControllerAnalogInputEvent;
import com.werwolv.api.event.input.controller.EnumControllerAxis;
import com.werwolv.api.event.input.keyboard.KeyHeldEvent;
import com.werwolv.api.event.input.keyboard.KeyTypedEvent;
import com.werwolv.api.event.input.mouse.MouseMovedEvent;
import com.werwolv.api.event.input.mouse.MousePressedEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.item.ItemStack;
import com.werwolv.main.ModMain;
import com.werwolv.states.GameState;
import com.werwolv.state.State;
import com.werwolv.tile.Tile;
import com.werwolv.tileEntities.TileEntity;

import static org.lwjgl.glfw.GLFW.*;

@EventBusSubscriber
public class EventHandler {

    private float cameraOffsetX, cameraOffsetY;

    @SubscribeEvent
    public void onKeyPressed(KeyTypedEvent event) {
        if(State.getCurrentState() instanceof GameState) {
            switch(event.getKeyCode()) {
                case GLFW_KEY_E:
                    if(API.thePlayer.getOpenedGui() == null)
                        API.thePlayer.openGui(ModMain.class, 0);
                    else API.thePlayer.closeGui();
                break;
                case GLFW_KEY_ESCAPE: API.thePlayer.closeGui(); break;
            }
        }
    }

    @SubscribeEvent
    public void onKeyHeld(KeyHeldEvent event) {
        switch(event.getKeyCode()) {
            case GLFW_KEY_W: API.thePlayer.move(0.0F, 0.1F); break;
            case GLFW_KEY_A: API.thePlayer.move(-0.1F, 0.0F); break;
            case GLFW_KEY_S: API.thePlayer.move(0.0F, -0.1F); break;
            case GLFW_KEY_D: API.thePlayer.move(0.1F, 0.0F); break;
        }
    }

    @SubscribeEvent
    public void onMouseMoved(MouseMovedEvent event) {

    }

    @SubscribeEvent
    public void onControllerAxisMoved(ControllerAnalogInputEvent event) {
        API.thePlayer.move(event.getAxis(EnumControllerAxis.STICK_LEFT_X) / 10, -event.getAxis(EnumControllerAxis.STICK_LEFT_Y) / 10);

        this.cameraOffsetX += event.getAxis(EnumControllerAxis.STICK_RIGHT_X);
        this.cameraOffsetY -= event.getAxis(EnumControllerAxis.STICK_RIGHT_Y);

        State.getCurrentState().getCamera().setOffset(cameraOffsetX, cameraOffsetY);
    }

    @SubscribeEvent
    public void onMousePressed(MousePressedEvent event) {
        if(State.getCurrentState() instanceof GameState) {
            GameState gameState = (GameState) State.getCurrentState();

            if(gameState.player.getOpenedGui() == null) {
                int currX = (int) Math.round(event.getCoords().getWorldSpaceX());
                int currY = (int) Math.round(event.getCoords().getWorldSpaceY());
                TileEntity tileEntity = gameState.world.getTileEntity(currX, currY);

                if (tileEntity != null)
                    tileEntity.onTileClicked(event.getButton(), gameState.player, gameState.world, currX / Tile.TILE_SIZE, currY / Tile.TILE_SIZE);

            } else if(API.thePlayer.getOpenedContainer() != null) {
                API.thePlayer.getOpenedContainer().getInventorySlots().forEach((key, slot) -> {
                    int slotX = API.ContextValues.WINDOW_WIDTH / 2 - slot.getSize() / 2 + slot.getPosX();
                    int slotY = API.ContextValues.WINDOW_HEIGHT / 2 - slot.getSize() / 2 + slot.getPosY();

                    if(event.getCoords().getX() >= slotX && event.getCoords().getY() >= slotY) {
                        if (event.getCoords().getX() <= slotX + slot.getSize() && event.getCoords().getY() <= slotY + slot.getSize()) {
                            ItemStack oldDraggedItem = gameState.player.getOpenedContainer().draggingItem;
                            gameState.player.getOpenedContainer().draggingItem = slot.getItemStack();
                            slot.setItemStack(oldDraggedItem);
                        }
                    }
                });
            }
        }
    }
}
