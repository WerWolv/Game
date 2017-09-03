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

import static java.awt.event.KeyEvent.*;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onKeyPressed(KeyTypedEvent event) {
        if(State.getCurrentState() instanceof GameState) {
            GameState gameState = (GameState) State.getCurrentState();

            switch(event.getKeyCode()) {
                case VK_E: {
                    if(gameState.player.getOpenedGui() == null)
                        gameState.player.openGui(ModMain.class, 0);
                    else
                        gameState.player.closeGui();
                } break;
                case VK_ESCAPE: gameState.player.closeGui(); break;
                case VK_K: gameState.player.getOpenedContainer().setInventorySlotContent(0, new ItemStack(ModMain.itemTest, 1, 0));
            }
        }
    }

    @SubscribeEvent
    public void onMouseMoved(MouseMovedEvent event) {
        State.mouseX = event.getCoords().getX();
        State.mouseY = event.getCoords().getY();
    }

    @SubscribeEvent
    public void onMousePressed(MousePressedEvent event) {
        if(State.getCurrentState() instanceof GameState) {
            GameState gameState = (GameState) State.getCurrentState();

            if(gameState.player.getOpenedGui() == null) {

                int currX = (int) Math.round(event.getCoords().getWorldSpaceX());
                int currY = (int) Math.round(event.getCoords().getWorldSpaceY());

                TileEntity tileEntity = gameState.world.getTileEntity(currX, currY);

                if (tileEntity != null) {
                    System.out.println(currX + ", " + currY);
                    tileEntity.onTileClicked(event.getButton(), gameState.player, gameState.world, currX / Tile.TILE_SIZE, currY / Tile.TILE_SIZE);
                }
            } else if(API.thePlayer.getOpenedContainer() != null) {
                API.thePlayer.getOpenedContainer().getInventorySlots().forEach((key, slot) -> {
                    int slotX = API.ContextValues.WINDOW_WIDTH / 2 - slot.getSize() / 2 + slot.getPosX();
                    int slotY = API.ContextValues.WINDOW_HEIGHT / 2 - slot.getSize() / 2 + slot.getPosY();

                    if(event.getCoords().getX() >= slotX && event.getCoords().getY() >= slotY) {
                        if (State.mouseX <= slotX + slot.getSize() && State.mouseY <= slotY + slot.getSize()) {
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
