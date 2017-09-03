package com.werwolv.main;

import com.werwolv.api.API;
import com.werwolv.api.event.init.GameDestroyEvent;
import com.werwolv.api.event.init.InitializationEvent;
import com.werwolv.api.event.init.PostInitializationEvent;
import com.werwolv.api.event.init.PreInitializationEvent;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.api.modloader.Mod;
import com.werwolv.data.SerializableDataObject;
import com.werwolv.handler.GuiHandler;
import com.werwolv.item.Item;
import com.werwolv.tile.Tile;
import com.werwolv.tile.TileTest;
import com.werwolv.tileEntities.TileEntityTest;

@Mod(modId = "game", modName = "Game", modVersion = "1.0.0", guiHandler = GuiHandler.class)
public class ModMain {

    public static final Tile tileGrass = new TileTest(1).setUnlocalizedName("tileGrass").setTexture("game:tileGrass").addTileEntity(TileEntityTest.class);

    public static final Item itemTest = new Item(1).setUnlocalizedName("itemTest").setTexture("game:itemGrass");

    @SubscribeEvent
    public void onPreInit(PreInitializationEvent event) {
        API.thePlayer.getPlayerData().deserialize();
    }

    @SubscribeEvent
    public void onInit(InitializationEvent event) {
        API.GameRegistry.registerTile(tileGrass);
        API.GameRegistry.registerItem(itemTest);
    }

    @SubscribeEvent
    public void onPostInit(PostInitializationEvent event) {

    }

    public void onGameDestroyed(GameDestroyEvent event) {
        API.thePlayer.getPlayerData().serialize();
    }

}
