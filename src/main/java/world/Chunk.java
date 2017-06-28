package world;

import com.werwolv.tile.Tile;

public class Chunk {

    public static final int CHUNK_WIDTH = 1;

    private int[][] tiles = new int[Chunk.CHUNK_WIDTH][World.WORLD_HEIGHT];

    public Chunk(int[][] tiles) {
        this.tiles = tiles;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public void setTile(Tile tile, int posX, int posY) {
        this.tiles[posX][posY] = tile.getTileID();
    }

    public void setTile(int tileId, int posX, int posY) {
        this.tiles[posX][posY] = tileId;
    }

}
