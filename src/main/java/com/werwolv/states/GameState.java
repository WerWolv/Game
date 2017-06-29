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

        for(int chunk = 0; chunk < world.getChunkCount(); chunk++)
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++)
                for (int y = 0; y < World.WORLD_HEIGHT; y++)
                    if (world.getChunk(chunk).getGridObjects()[x][y] != null && world.getChunk(chunk).getGridObjects()[x][y].getTileID() != 0) {
                        if(x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE < -Tile.TILE_SIZE || x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                            continue;
                        if(y * Tile.TILE_SIZE - 2 - camera.getY() < -Tile.TILE_SIZE || y * Tile.TILE_SIZE - 2 - camera.getY() > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                            continue;

                        g.fillRect((int)(x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE), (int)(y * Tile.TILE_SIZE - 2 - camera.getY()), Tile.TILE_SIZE, Tile.TILE_SIZE + 4);
                        g.fillRect((int)(x * Tile.TILE_SIZE - 2 - camera.getX() + chunk * Tile.TILE_SIZE), (int)(y * Tile.TILE_SIZE - camera.getY()), Tile.TILE_SIZE + 4, Tile.TILE_SIZE);
                    }

        for(int chunk = 0; chunk < world.getChunkCount(); chunk++)
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++)
                for (int y = 0; y < World.WORLD_HEIGHT; y++) {
                    if (world.getChunk(chunk).getGridObjects()[x][y] != null && world.getChunk(chunk).getGridObjects()[x][y].getTileID() != 0) {
                        if (x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE < -Tile.TILE_SIZE || x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                            continue;
                        if (y * Tile.TILE_SIZE - 2 - camera.getY() < -Tile.TILE_SIZE || y * Tile.TILE_SIZE - 2 - camera.getY() > Game.INSTANCE.getWindowWidth() + Tile.TILE_SIZE)
                            continue;

                        int tileId = world.getChunk(chunk).getGridObjects()[x][y].getTileID();
                        if (API.TileRegistry.getTileFromID(tileId) != null && tileId != 0)
                            g.drawImage(API.ResourceRegistry.getResourceFromID(API.TileRegistry.getTileFromID(tileId).getTextureID()), (int) (x * Tile.TILE_SIZE - camera.getX() + chunk * Tile.TILE_SIZE), (int) (y * Tile.TILE_SIZE - camera.getY()), Tile.TILE_SIZE, Tile.TILE_SIZE, null);
                    }
                }

        g.fillRect((int)this.player.getPosX() - (int)this.camera.getX() + Game.INSTANCE.getWindowWidth() / 2, (int)this.player.getPosY() - (int)this.camera.getY() + Game.INSTANCE.getWindowHeight() / 2, 20, 20);
    }
}
