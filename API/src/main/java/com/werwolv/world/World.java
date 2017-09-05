package com.werwolv.world;

import com.werwolv.api.API;
import com.werwolv.api.IUpdatable;
import com.werwolv.api.Log;
import com.werwolv.api.event.entity.EntityDiedEvent;
import com.werwolv.entities.Entity;
import com.werwolv.tile.Tile;
import com.werwolv.tileEntities.TileEntity;
import javafx.util.Pair;

import java.util.*;

public class World implements IUpdatable {

    public static final int WORLD_HEIGHT = 512;

    private List<Entity> entities = new ArrayList<>();
    private Map<Integer, Chunk<Integer>> chunksTile = new HashMap<>();
    private Map<Integer, Chunk<TileEntity>> chunksTileEntity = new HashMap<>();

    public void update(long deltaTime) {
        Entity entityToRemove = null;
        for(Iterator iter = entities.iterator(); iter.hasNext(); entityToRemove = (Entity) iter.next())
            if(entityToRemove != null && entityToRemove.isDead()) {
                API.EVENT_BUS.postEvent(new EntityDiedEvent(entityToRemove, entityToRemove.getX(), entityToRemove.getY()));
                iter.remove();
            }
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
    }

    public Chunk<Integer> getTileChunk(int coord) {
        if(!chunksTile.containsKey(coord))
            this.chunksTile.put(coord, new Chunk());

        return chunksTile.get(coord);
    }

    public Chunk<TileEntity> getTileEntityChunk(int coord) {
        if(!chunksTileEntity.containsKey(coord))
            this.chunksTileEntity.put(coord, new Chunk());

        return chunksTileEntity.get(coord);
    }

    public int getChunkCount() {
        return chunksTile.size();
    }

    public Tile getTile(int posX, int posY) {
        int chunk = (int)Math.floor(posX / Chunk.CHUNK_WIDTH);

        if(chunksTile.get(chunk) == null)
            return null;

        return API.GameRegistry.getTileFromID(chunksTile.get(chunk).getGridObjects().get(new Pair<>(posX % Chunk.CHUNK_WIDTH, posY)));
    }

    public void setTile(Tile tile, int posX, int posY) {
        int chunk = (int)Math.floor(posX / Chunk.CHUNK_WIDTH);

        if(tile == null) return;

        if(!chunksTile.containsKey(chunk))
            this.chunksTile.put(chunk, new Chunk());

        this.getTileChunk(chunk).setGridObject(tile.getTileID(), posX - (chunk * Chunk.CHUNK_WIDTH), posY);

        if(tile.getTileEntity() != null)
            this.getTileEntityChunk(chunk).setGridObject(tile.getTileEntity(), posX - (chunk * Chunk.CHUNK_WIDTH), posY);
    }

    public void setTile(int id, int posX, int posY) {
        Tile tile = API.GameRegistry.getTileFromID(id);

        if(id != 0 && tile == null) {
            Log.wtf("World", "Tile id " + id + " doesn't correspond to a registered tile!");
            return;
        }

        this.setTile(tile, posX, posY);

    }

    public TileEntity getTileEntity(int posX, int posY) {
        int chunk = (int)Math.floor(posX / Chunk.CHUNK_WIDTH);

        if(chunksTileEntity.get(chunk) == null)
            return null;

        if(posY < 0 || posY >= World.WORLD_HEIGHT)
            return null;

        if(chunksTileEntity.get(chunk).getGridObjects().get(new Pair<>(posX % Chunk.CHUNK_WIDTH, posY)) == null)
            return null;

        return chunksTileEntity.get(chunk).getGridObjects().get(new Pair<>(posX % Chunk.CHUNK_WIDTH, posY));
    }

}
