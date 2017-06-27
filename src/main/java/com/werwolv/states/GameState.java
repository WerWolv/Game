package com.werwolv.states;

import com.werwolv.api.API;
import com.werwolv.tile.Tile;

import java.awt.*;

public class GameState extends State{

    public static final int WORLD_WIDTH = 4096;
    public static final int WORLD_HEIGHT = 512;

	private int[][] worldTiles = new int[WORLD_WIDTH][WORLD_HEIGHT];

	public GameState() {
		worldTiles[0][0] = 2;
        worldTiles[0][1] = 1;
        worldTiles[1][1] = 1;
        worldTiles[0][2] = 2;

    }

	@Override
	public void render(Graphics2D g) {
	    g.setColor(Color.BLACK);

        for(int x = 0; x < WORLD_WIDTH; x++)
            for (int y = 0; y < WORLD_HEIGHT; y++)
                if (worldTiles[x][y] != 0) {
                    g.fillRect(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE - 2, Tile.TILE_SIZE, Tile.TILE_SIZE + 4);
                    g.fillRect(x * Tile.TILE_SIZE - 2, y * Tile.TILE_SIZE, Tile.TILE_SIZE + 4, Tile.TILE_SIZE);
                }

	    for(int x = 0; x < WORLD_WIDTH; x++)
            for (int y = 0; y < WORLD_HEIGHT; y++)
                if (worldTiles[x][y] != 0)
                    if(API.TileRegistry.getTileFromID(worldTiles[x][y]) != null)
                            g.drawImage(API.ResourceRegistry.getResourceFromID(API.TileRegistry.getTileFromID(worldTiles[x][y]).getTextureID()), x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);

    }

	@Override
	public void update() {
    }

}
