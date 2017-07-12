package com.werwolv.world;

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
                this.world.setTile(random.nextInt(3), chunkStart, y);
    }

    public long getSeed() {
        return seed;
    }
}
