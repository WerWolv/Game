package com.werwolv.world;

import com.werwolv.api.API;

import java.util.Random;

public class WorldGenerator {

    private World world;
    private long seed;

    Random random = new Random();

    public WorldGenerator(World world, long seed) {
        this.world = world;
        this.seed = seed;
    }

    public void generate(int chunkStart, int chunkEnd) {
        random.setSeed(this.seed);
        for(; chunkStart < chunkEnd; chunkStart++)
            for (int y = 0; y < World.WORLD_HEIGHT; y++)
                this.world.setTile(API.TileRegistry.getTileFromID(random.nextInt(3)), chunkStart, y);
    }

    public long getSeed() {
        return seed;
    }
}
