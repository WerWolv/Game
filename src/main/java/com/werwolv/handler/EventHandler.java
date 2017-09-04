package com.werwolv.handler;

import com.werwolv.api.API;
import com.werwolv.api.event.input.KeyTypedEvent;
import com.werwolv.api.event.input.MouseMovedEvent;
import com.werwolv.api.event.input.MousePressedEvent;
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
    public void onMouseMoved(MouseMovedEvent event) {

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
