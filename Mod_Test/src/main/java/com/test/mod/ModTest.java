package com.test.mod;

import com.test.mod.tile.TileMod;
import com.werwolv.api.API;
import com.werwolv.api.Log;
import com.werwolv.api.event.init.InitializationEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.api.modloader.Mod;
import com.werwolv.tile.Tile;

@Mod(modId = "testId", modName = "testName", modVersion = "1.0.0")
@EventBusSubscriber
public class ModTest {

    public static final Tile tileMod = new TileMod(2).setUnlocalizedName("tileTest").setTexture("testId:tileTest");

    @SubscribeEvent
    public void onInit(InitializationEvent event) {
        API.GameRegistry.registerTile(ModTest.tileMod);
    }

}
