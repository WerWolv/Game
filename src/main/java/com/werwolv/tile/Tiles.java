package com.werwolv.tile;

import com.werwolv.api.API;
import com.werwolv.api.event.init.PreInitializationEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;

@EventBusSubscriber
public class Tiles {

    public static final Tile tileGrass = new Tile(1).setUnlocalizedName("tileGrass").setTexture("game:tileGrass");

    @SubscribeEvent
    public void onPreInit(PreInitializationEvent event) {
        API.TileRegistry.registerTile(tileGrass);
    }

}
