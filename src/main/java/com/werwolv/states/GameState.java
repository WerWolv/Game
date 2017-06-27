package com.werwolv.states;

import com.werwolv.api.API;
import com.werwolv.handler.EventHandler;
import com.werwolv.tile.Tile;
import com.werwolv.tile.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameState extends State{

    public static final int WORLD_WIDTH = 4096;
    public static final int WORLD_HEIGHT = 512;

	private int[][] worldTiles = new int[WORLD_WIDTH][WORLD_HEIGHT];

	public GameState() {
	    API.TileRegistry.registerTile(Tiles.tileGrass);

		worldTiles[0][0] = 2;
        worldTiles[0][1] = 1;
        worldTiles[0][2] = 2;

    }

	@Override
	public void render(Graphics2D g) {
	    g.setColor(Color.BLACK);

        for(int x = 0; x < WORLD_WIDTH; x++)
            for (int y = 0; y < WORLD_HEIGHT; y++)
                if (worldTiles[x][y] != 0) {
                    g.fillRect(x * Tiles.TILE_SIZE, y * Tiles.TILE_SIZE - 2, Tiles.TILE_SIZE, Tiles.TILE_SIZE + 4);
                    g.fillRect(x * Tiles.TILE_SIZE - 2, y * Tiles.TILE_SIZE, Tiles.TILE_SIZE + 4, Tiles.TILE_SIZE);
                }

	    for(int x = 0; x < WORLD_WIDTH; x++)
            for (int y = 0; y < WORLD_HEIGHT; y++)
                if (worldTiles[x][y] != 0)
                    if(API.TileRegistry.getTileFromID(worldTiles[x][y]) != null)
                            g.drawImage(API.ResourceRegistry.getResourceFromID(API.TileRegistry.getTileFromID(worldTiles[x][y]).getTextureID()).getImage(), x * Tiles.TILE_SIZE, y * Tiles.TILE_SIZE, Tiles.TILE_SIZE, Tiles.TILE_SIZE, null);

    }

	@Override
	public void update() {
    }

}
