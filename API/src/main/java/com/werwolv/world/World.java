package com.werwolv.world;

import com.werwolv.api.API;
import com.werwolv.api.IUpdatable;
import com.werwolv.api.event.entity.EntityDiedEvent;
import com.werwolv.entities.Entity;
import com.werwolv.tile.Tile;
import com.werwolv.tileEntities.TileEntity;

import java.util.*;

public class World implements IUpdatable {

    public static final int WORLD_HEIGHT = 512;

    private List<Entity> entities = new ArrayList<>();
    private Map<Integer, Chunk<Tile>> chunksTile = new HashMap<>();

    public World() {

    }

    public void update(long deltaTime) {
        Entity entityToRemove = null;
        for(Iterator iter = entities.iterator(); iter.hasNext(); entityToRemove = (Entity) iter.next())
            if(entityToRemove != null && entityToRemove.isDead()) {
                API.EVENT_BUS.postEvent(new EntityDiedEvent(entityToRemove, entityToRemove.getPosX(), entityToRemove.getPosY()));
                iter.remove();
            }
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
    }

    public Chunk<Tile> getChunk(int coord) {
        if(!chunksTile.containsKey(coord))
            this.chunksTile.put(coord, new Chunk(new Tile[Chunk.CHUNK_WIDTH][World.WORLD_HEIGHT]));

        return chunksTile.get(coord);
    }

    public int getChunkCount() {
        return chunksTile.size();
    }

    public Tile getTile(int posX, int posY) {
        int chunk = (int)Math.floor(posX / Chunk.CHUNK_WIDTH);

        if(chunksTile.get(chunk) == null)
            return null;

        return chunksTile.get(chunk).getGridObjects()[posX % Chunk.CHUNK_WIDTH][posY];
    }

    public void setTile(Tile tile, int posX, int posY) {
        int chunk = (int)Math.floor(posX / Chunk.CHUNK_WIDTH);

        if(!chunksTile.containsKey(chunk))
            this.chunksTile.put(chunk, new Chunk(new Tile[Chunk.CHUNK_WIDTH][World.WORLD_HEIGHT]));

        this.getChunk(chunk).setGridObject(tile, posX - (chunk * Chunk.CHUNK_WIDTH), posY);
    }

    public TileEntity getTileEntity(int posX, int posY) {
        int chunk = (int)Math.floor(posX / Chunk.CHUNK_WIDTH);

        if(chunksTile.get(chunk) == null)
            return null;

        if(posY < 0 || posY >= World.WORLD_HEIGHT)
            return null;

        if(chunksTile.get(chunk).getGridObjects()[posX % Chunk.CHUNK_WIDTH][posY] == null)
            return null;

        return chunksTile.get(chunk).getGridObjects()[posX % Chunk.CHUNK_WIDTH][posY].getTileEntity();
    }

}
