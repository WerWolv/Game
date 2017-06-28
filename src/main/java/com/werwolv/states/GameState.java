package com.werwolv.states;

import com.sun.glass.events.KeyEvent;
import com.werwolv.api.API;
import com.werwolv.handler.EventHandler;
import com.werwolv.handler.KeyHandler;
import com.werwolv.random.Terrain;
import com.werwolv.tile.Tile;
import com.werwolv.tile.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameState extends State{

    public static final int WORLD_WIDTH = 4096;
    public static final int WORLD_HEIGHT = 512;

	private int[][] worldTiles = new int[WORLD_WIDTH][WORLD_HEIGHT];

	private Terrain terrain = new Terrain(512,1024, 0);

	private static int[] camera = {0,0};



	public GameState() {
        System.arraycopy(terrain.generate(), 0, worldTiles, 0, terrain.getWidth());
    }

	@Override
	public void render(Graphics2D g) {
	    g.setColor(Color.BLACK);

        for(int x = 0; x < WORLD_WIDTH; x++)
            for (int y = 0; y < WORLD_HEIGHT; y++)
                if (worldTiles[x][y] != 0) {
                    g.fillRect(x * Tile.TILE_SIZE + camera[0], y * Tile.TILE_SIZE - 2 + camera[1], Tile.TILE_SIZE, Tile.TILE_SIZE + 4);
                    g.fillRect(x * Tile.TILE_SIZE - 2 + camera[0], y * Tile.TILE_SIZE + camera[1], Tile.TILE_SIZE + 4, Tile.TILE_SIZE);
                }

	    for(int x = 0; x < WORLD_WIDTH; x++)
            for (int y = 0; y < WORLD_HEIGHT; y++)
                if (worldTiles[x][y] != 0)
                    if(API.TileRegistry.getTileFromID(worldTiles[x][y]) != null) {
                        g.drawImage(API.ResourceRegistry.getResourceFromID(API.TileRegistry.getTileFromID(worldTiles[x][y]).getTextureID()), x * Tile.TILE_SIZE + camera[0], y * Tile.TILE_SIZE + camera[1], Tile.TILE_SIZE, Tile.TILE_SIZE, null);
        }
    }

	@Override
	public void update() {
        if(KeyHandler.isKeyPressed(KeyEvent.VK_S)) {
            GameState.changecamera(0,-5);
        }
        if(KeyHandler.isKeyPressed(KeyEvent.VK_W)) {
            GameState.changecamera(0,5);
        }
        if(KeyHandler.isKeyPressed(KeyEvent.VK_A)) {
            GameState.changecamera(5,0);
        }
        if(KeyHandler.isKeyPressed(KeyEvent.VK_D)) {
            GameState.changecamera(-5,0);
        }
    }

    public static void changecamera(int x, int y)
    {
        camera[0] += x;
        camera[1] += y;
    }

}
