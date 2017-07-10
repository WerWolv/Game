package com.werwolv.states;

import com.sun.glass.events.KeyEvent;
import com.werwolv.api.API;
import com.werwolv.container.Container;
import com.werwolv.engine.renderer.TileRenderer;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.main.Camera;
import com.werwolv.main.Game;
import com.werwolv.main.Window;
import com.werwolv.tile.Tile;
import com.werwolv.world.Chunk;
import com.werwolv.world.World;
import com.werwolv.world.WorldGenerator;
import org.joml.Matrix4f;

public class GameState extends State{

    private static final float WORLD_SCALE = 50.0F;

    public World world;
    public EntityPlayer player;

    public Camera camera;

    public TileRenderer tileRenderer = new TileRenderer();
    private Matrix4f worldSpace = new Matrix4f().scale(WORLD_SCALE);

    public GameState() {
	    this.world = new World();
	    this.player = new EntityPlayer(this.world, 0, 0);
	    this.camera = new Camera();

	    this.camera.setEntityToFollow(this.player);
	    this.camera.setLerp(0.1F);

        this.world.spawnEntity(this.player);
    }

    @Override
    public void init() {
        WorldGenerator worldGen = new WorldGenerator(this.world, 123);
        worldGen.generate(0, 256);
    }

    @Override
    public void update(long delta) {
        if(Window.isKeyPressed(KeyEvent.VK_W))
            this.player.move(0,0.1);
        if(Window.isKeyPressed(KeyEvent.VK_A))
            this.player.move(-0.1,0);
        if(Window.isKeyPressed(KeyEvent.VK_S))
            this.player.move(0,-0.1);
        if(Window.isKeyPressed(KeyEvent.VK_D))
            this.player.move(0.1,0);
    }

	@Override
	public void render() {
        float width = API.ContextValues.WINDOW_WIDTH;
        float height = API.ContextValues.WINDOW_HEIGHT;

        int chunksOnScreen = (int )Math.ceil(width / (Chunk.CHUNK_WIDTH * WORLD_SCALE));
        int verticalTilesOnScreen = (int) Math.ceil((height / WORLD_SCALE));

        int cameraChunk = (int) (camera.getX());
        int cameraVerticalTile = (int)(camera.getY());

        for(int chunk = cameraChunk - chunksOnScreen / 2 - 1; chunk < cameraChunk + chunksOnScreen / 2 + 1; chunk++) {
            for(int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
                for (int y = Math.max(0, cameraVerticalTile - verticalTilesOnScreen / 2 - 1); y < cameraVerticalTile + verticalTilesOnScreen / 2 + 1; y++) {
                    Tile tile = this.world.getChunk(chunk).getGridObjects()[x][y];
                    if(tile != null && tile.getTileID() != 0) {
                        tileRenderer.renderTile(tile.getTileID(), chunk * Chunk.CHUNK_WIDTH + x, y, worldSpace, camera);
                    }
                }
            }
        }

        tileRenderer.renderTile(2, (float) player.getPosX(), (float) player.getPosY(), worldSpace, camera);

    }
}
