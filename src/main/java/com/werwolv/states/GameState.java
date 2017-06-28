package com.werwolv.states;

import com.sun.glass.events.KeyEvent;
import com.werwolv.api.API;
import com.werwolv.handler.KeyHandler;
import com.werwolv.main.Game;
import com.werwolv.tile.Tile;
import com.werwolv.world.Chunk;
import com.werwolv.world.World;
import com.werwolv.world.WorldGenerator;

import java.awt.*;

public class GameState extends State{

	private World world = new World();

	private static int[] camera = {0,0};

	public GameState() {
        WorldGenerator worldGen = new WorldGenerator(this.world, 123);
        worldGen.generate(0, 256);
    }

	@Override
	public void render(Graphics2D g) {
	    g.setColor(Color.BLACK);

        for(int chunk = 0; chunk < world.getChunkCount(); chunk++)
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++)
                for (int y = 0; y < World.WORLD_HEIGHT; y++)
                    if (world.getChunk(chunk).getTiles()[x][y] != 0) {
                        if(x * Tile.TILE_SIZE + camera[0] + chunk * Tile.TILE_SIZE < -Tile.TILE_SIZE || x * Tile.TILE_SIZE + camera[0] + chunk * Tile.TILE_SIZE > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                            continue;
                        if(y * Tile.TILE_SIZE - 2 + camera[1] < -Tile.TILE_SIZE || y * Tile.TILE_SIZE - 2 + camera[1] > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                            continue;

                        g.fillRect(x * Tile.TILE_SIZE + camera[0] + chunk * Tile.TILE_SIZE, y * Tile.TILE_SIZE - 2 + camera[1], Tile.TILE_SIZE, Tile.TILE_SIZE + 4);
                        g.fillRect(x * Tile.TILE_SIZE - 2 + camera[0] + chunk * Tile.TILE_SIZE, y * Tile.TILE_SIZE + camera[1], Tile.TILE_SIZE + 4, Tile.TILE_SIZE);
                    }

        for(int chunk = 0; chunk < world.getChunkCount(); chunk++)
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++)
                for (int y = 0; y < World.WORLD_HEIGHT; y++) {

                    if(x * Tile.TILE_SIZE + camera[0] + chunk * Tile.TILE_SIZE < -Tile.TILE_SIZE || x * Tile.TILE_SIZE + camera[0] + chunk * Tile.TILE_SIZE > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                        continue;
                    if(y * Tile.TILE_SIZE - 2 + camera[1] < -Tile.TILE_SIZE || y * Tile.TILE_SIZE - 2 + camera[1] > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                        continue;

	                int tileId = world.getChunk(chunk).getTiles()[x][y];
                        if (API.TileRegistry.getTileFromID(tileId) != null && tileId != 0)
                            g.drawImage(API.ResourceRegistry.getResourceFromID(API.TileRegistry.getTileFromID(tileId).getTextureID()), x * Tile.TILE_SIZE + camera[0] + chunk * Tile.TILE_SIZE, y * Tile.TILE_SIZE + camera[1], Tile.TILE_SIZE, Tile.TILE_SIZE, null);
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
