package com.werwolv.random;


import javafx.scene.canvas.Canvas;

import java.util.Random;

public class Terrain {
    private int height;
    private int width;
    private long seed;

    private boolean isgenerated = false;


    private int[][] terrain;

    Random rand;

    public Terrain(int height, int width, long seed) {
        this.height = height;
        this.width = width;
        if(seed!=0)
        {
            this.seed = seed;
            rand = new Random(seed);
        }
        else
        {
            this.seed = System.currentTimeMillis();
            rand = new Random();
        }
        terrain = new int[width][height];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getSeed() {
        return seed;
    }

    public boolean isGenerated() {
        return isgenerated;
    }

    public int[][] getTerrain() {
        return terrain;
    }

    public int[][] generate()
    {
        for(int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                terrain[x][y] = rand.nextInt(2);

        isgenerated = true;
        return terrain;
    }

    public void render()
    {
        ;
    }
}
