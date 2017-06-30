package com.werwolv.main;

import com.werwolv.api.API;
import com.werwolv.api.event.init.InitializationEvent;
import com.werwolv.api.event.init.PostInitializationEvent;
import com.werwolv.api.event.init.PreInitializationEvent;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.api.modloader.Mod;
import com.werwolv.handler.GuiHandler;
import com.werwolv.tile.Tile;
import com.werwolv.tileEntities.TileEntityTest;

@Mod(modId = "game", modName = "Game", modVersion = "1.0.0", guiHandler = GuiHandler.class)
public class ModMain {

    public static final Tile tileGrass = new Tile(1).setUnlocalizedName("tileGrass").setTexture("game:tileGrass").addTileEntity(TileEntityTest.class);

    @SubscribeEvent
    public void onPreInit(PreInitializationEvent event) {

    }

    @SubscribeEvent
    public void onInit(InitializationEvent event) {
        API.TileRegistry.registerTile(tileGrass);
    }

    @SubscribeEvent
    public void onPostInit(PostInitializationEvent event) {

    }

}
