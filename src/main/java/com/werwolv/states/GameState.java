package com.werwolv.states;

import com.sun.glass.events.KeyEvent;
import com.werwolv.api.API;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.handler.KeyHandler;
import com.werwolv.main.Camera;
import com.werwolv.main.Game;
import com.werwolv.tile.Tile;
import com.werwolv.world.Chunk;
import com.werwolv.world.World;
import com.werwolv.world.WorldGenerator;

import java.awt.*;

public class GameState extends State{

	public World world;
    public EntityPlayer player;

    public Camera camera;

	public GameState() {
	    this.world = new World();
	    this.player = new EntityPlayer(this.world, 0, 0);
	    this.camera = new Camera();

	    this.camera.setEntityToFollow(this.player);
	    this.camera.setLerp(0.02F);

        this.world.spawnEntity(this.player);
    }

    @Override
    public void init() {
        WorldGenerator worldGen = new WorldGenerator(this.world, 123);
        worldGen.generate(0, 256);
    }

    @Override
    public void update(long delta) {
        if(KeyHandler.isKeyPressed(KeyEvent.VK_W))
            this.player.move(0,-1);
        if(KeyHandler.isKeyPressed(KeyEvent.VK_A))
            this.player.move(-1,0);
        if(KeyHandler.isKeyPressed(KeyEvent.VK_S))
            this.player.move(0,1);
        if(KeyHandler.isKeyPressed(KeyEvent.VK_D))
            this.player.move(1,0);
    }

	@Override
	public void render(Graphics2D g) {
	    g.setColor(Color.BLACK);

	    int windowWidth = Game.INSTANCE.getWindowWidth();
	    int windowHeight = Game.INSTANCE.getWindowHeight();

	    int chunksOnScreen = (windowWidth / (Chunk.CHUNK_WIDTH * Tile.TILE_SIZE));
	    int verticalTilesOnScreen = windowHeight / Tile.TILE_SIZE;

        int cameraChunk = (int) (camera.getX() + windowWidth / 2) / Tile.TILE_SIZE / Chunk.CHUNK_WIDTH;
        int cameraVerticalTile = (int)(camera.getY() + windowHeight / 2) / Tile.TILE_SIZE;

        for(int chunk = cameraChunk - chunksOnScreen / 2 - 1; chunk < cameraChunk + chunksOnScreen / 2 + 1; chunk++)
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++)
                for (int y = Math.max(0, cameraVerticalTile - verticalTilesOnScreen / 2 - 1); y < cameraVerticalTile + verticalTilesOnScreen / 2 + 1; y++)
                    if (world.getChunk(chunk).getGridObjects()[x][y] != null && world.getChunk(chunk).getGridObjects()[x][y].getTileID() != 0) {

                        g.fillRect((int)(x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE), (int)(y * Tile.TILE_SIZE - 2 - camera.getY()), Tile.TILE_SIZE, Tile.TILE_SIZE + 4);
                        g.fillRect((int)(x * Tile.TILE_SIZE - 2 - camera.getX() + chunk * Tile.TILE_SIZE), (int)(y * Tile.TILE_SIZE - camera.getY()), Tile.TILE_SIZE + 4, Tile.TILE_SIZE);
                    }

        for(int chunk = cameraChunk - chunksOnScreen / 2 - 1; chunk < cameraChunk + chunksOnScreen / 2 + 1; chunk++)
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++)
                for (int y = Math.max(0, cameraVerticalTile - verticalTilesOnScreen / 2 - 1); y < cameraVerticalTile + verticalTilesOnScreen / 2 + 1; y++) {
                    if (world.getChunk(chunk).getGridObjects()[x][y] != null && world.getChunk(chunk).getGridObjects()[x][y].getTileID() != 0) {

                        int tileId = world.getChunk(chunk).getGridObjects()[x][y].getTileID();
                        if (API.TileRegistry.getTileFromID(tileId) != null && tileId != 0)
                            g.drawImage(API.ResourceRegistry.getResourceFromID(API.TileRegistry.getTileFromID(tileId).getTextureID()), (int) (x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE), (int) (y * Tile.TILE_SIZE - camera.getY()), Tile.TILE_SIZE, Tile.TILE_SIZE, null);
                    }
                }

        g.fillRect((int)this.player.getPosX() - (int)this.camera.getX(), (int)this.player.getPosY() - (int)this.camera.getY(), 20, 20);


        if(this.player.getOpenedGui() != null)
            this.player.getOpenedGui().render(g);
	}
}
