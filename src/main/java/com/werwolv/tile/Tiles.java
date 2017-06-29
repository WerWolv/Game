package com.werwolv.tile;

import com.werwolv.api.API;
import com.werwolv.api.event.init.InitializationEvent;
import com.werwolv.api.eventbus.EventBusSubscriber;
import com.werwolv.api.eventbus.SubscribeEvent;
import com.werwolv.tileEntities.TileEntityTest;

@EventBusSubscriber
public class Tiles {

    public static final Tile tileGrass = new Tile(1).setUnlocalizedName("tileGrass").setTexture("game:tileGrass").addTileEntity(TileEntityTest.class);

    @SubscribeEvent
    public void onInit(InitializationEvent event) {
        API.TileRegistry.registerTile(tileGrass);
    }

}
