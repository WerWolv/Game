package com.werwolv.world;

import java.util.Random;

public class WorldGenBase {

    private World world;
    private long seed;

    private final int ground_level = 350;

    Random random = new Random();

    public WorldGenBase(World world, long seed) {
        this.world = world;
        this.seed = seed;
    }

    public void generateChunk(int chunkStart, int chunkEnd) {
        random.setSeed(this.seed);
        generateNoise(chunkStart, chunkEnd, World.WORLD_HEIGHT, 4, 0.5f, 0.01f);
    }

    public long getSeed() {
        return seed;
    }

    public float cutHeight(int height, int cutheight, int frequency) {
        return (height>cutheight ? (((float)height - cutheight) / frequency) : 0);
    }


    public void generateNoise(int chunkStart, int chunkEnd, int height, int octaves, float roughness, float scale) {
        float[][] bufferNoise = new float[chunkEnd-chunkStart][height];

        float layerFrequency = scale;
        float layerWeight = 1;
        float weightSum = 0;
        SimplexNoise simplexNoise = new SimplexNoise(0);


        float bufferlayerheight = layerFrequency;
        for (int octave = 0; octave < octaves; octave++) {
            //Calculate single layer/octave of simplex noise, then add it to total noise
            for(int y = 0; y < height; y++){
                for(int x = chunkStart; x < chunkEnd; x++){
                    bufferNoise[x-chunkStart][y] += (float) simplexNoise.noise(x * layerFrequency,y * layerFrequency) * layerWeight;
                }
            }
            //Increase variables with each incrementing octave
            layerFrequency *= 2;
            weightSum += layerWeight;
            layerWeight *= roughness;
        }




        for(int x = chunkStart; x < chunkEnd; x++) {
            for (int y = 0; y < height; y++) {
                bufferNoise[x-chunkStart][y] -= cutHeight(y,ground_level,20);
                this.world.setTile(bufferNoise[x-chunkStart][y] > 0.0f ? (bufferNoise[x-chunkStart][y] > 0.5f ? 2 : 1) : 0, x, y);
            }
        }
    }
}
