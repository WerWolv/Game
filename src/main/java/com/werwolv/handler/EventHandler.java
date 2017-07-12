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
import com.werwolv.states.State;
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
        State.mouseX = event.getX();
        State.mouseY = event.getY();
    }

    @SubscribeEvent
    public void onMousePressed(MousePressedEvent event) {
        if(State.getCurrentState() instanceof GameState) {
            GameState gameState = (GameState) State.getCurrentState();

            if(gameState.player.getOpenedGui() == null) {

                int currX = (int) (gameState.camera.getX() + event.getX());
                int currY = (int) (gameState.camera.getY() + event.getY());

                TileEntity tileEntity = gameState.world.getTileEntity(currX / Tile.TILE_SIZE, currY / Tile.TILE_SIZE);

                if (tileEntity != null)
                    tileEntity.onTileClicked(event.button, gameState.player, gameState.world, currX / Tile.TILE_SIZE, currY / Tile.TILE_SIZE);
                //else if(gameState.player.inventoryPlayer.get)
            } else {
                gameState.player.getOpenedContainer().getInventorySlots().forEach((key, slot) -> {
                    int slotX = API.ContextValues.WINDOW_WIDTH / 2 - slot.getSize() / 2 + slot.getPosX();
                    int slotY = API.ContextValues.WINDOW_HEIGHT / 2 - slot.getSize() / 2 + slot.getPosY();

                    if(State.mouseX >= slotX && State.mouseY >= slotY) {
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
