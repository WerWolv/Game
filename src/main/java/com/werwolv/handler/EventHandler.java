package com.werwolv.handler;

import com.werwolv.api.API;
import com.werwolv.api.event.input.KeyPressedEvent;
import com.werwolv.api.event.input.MouseClickedEvent;
import com.werwolv.api.event.input.MousePressedEvent;
import com.werwolv.api.event.input.MouseReleasedEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.main.Game;
import com.werwolv.states.GameState;
import com.werwolv.states.State;
import com.werwolv.tile.Tile;
import com.werwolv.tileEntities.TileEntity;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onKeyPressed(KeyPressedEvent event) {

    }

    @SubscribeEvent
    public void onMousePressed(MousePressedEvent event) {
        if(State.getCurrentState() instanceof GameState) {
            GameState gameState = (GameState) State.getCurrentState();
            int currX = (int)(gameState.camera.getX() + event.getX());
            int currY = (int)(gameState.camera.getY() + event.getY());

            TileEntity tileEntity = gameState.world.getTileEntity(currX / Tile.TILE_SIZE, currY / Tile.TILE_SIZE);

            if(tileEntity != null)
                tileEntity.onTileClicked(event.button, gameState.player, gameState.world, currX / Tile.TILE_SIZE, currY / Tile.TILE_SIZE);
            //else if(gameState.player.inventoryPlayer.get)
        }
    }
}
