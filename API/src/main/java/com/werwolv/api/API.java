package com.werwolv.api;

import com.werwolv.api.eventbus.EventBus;
import com.werwolv.api.modloader.ModLoader;
import com.werwolv.renderer.GuiRenderer;
import com.werwolv.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class API {

    public static final EventBus EVENT_BUS = new EventBus();
    public static final ModLoader MOD_LOADER = new ModLoader();

    public static class TileRegistry {
        private static Map<Integer, Tile> registeredTiles = new HashMap<>();

        public static void registerTile(Tile tile) {
            if(registeredTiles.containsKey(tile.getTileID())) {
                Log.wtf("TileRegistry", "Tile with ID " + tile.getTileID() + " is already registered!");
                return;
            }

            registeredTiles.put(tile.getTileID(), tile);
        }

        public static void unregisterTile(Tile tile) {
            if(!registeredTiles.containsKey(tile.getTileID())) {
                Log.wtf("TileRegistry", "Tile with ID " + tile.getTileID() + " isn't registered! Can't unregister!");
                return;
            }

                registeredTiles.remove(tile.getTileID());
        }

        public static void unregisterTile(int tileID) {
            if(!registeredTiles.containsKey(tileID)) {
                Log.wtf("TileRegistry", "Tile with ID " + tileID + " isn't registered! Can't unregister!");
                return;
            }

            registeredTiles.remove(tileID);
        }

        public static Tile getTileFromID(int id) {
            return registeredTiles.get(id);
        }
    }

    public static class ResourceRegistry {
        private static Map<Integer, BufferedImage> loadedResources = new HashMap<>();

        private static int registeredTextures = 0;

        public static int registerResource(String path) {
            int textureID;

            if(ClassLoader.getSystemClassLoader().getResource(path) == null) {
                Log.wtf("ResourceRegistry", "Cannot load file " + path);
                return -1;
            }

            registeredTextures++;

            try {
                for(textureID = 0; textureID < registeredTextures; textureID++) {
                    if (loadedResources.get(textureID) == null) {
                        loadedResources.put(textureID, ImageIO.read(ClassLoader.getSystemClassLoader().getResource(path)));
                        break;
                    }
                }

                Log.d("ResourceRegistry", "Loaded resource " + path + " as texture ID " + textureID);

                return textureID;

            } catch(IOException e) {
                Log.wtf("ResourceRegistry", "Cannot load " + path);
            }

            return -1;
        }

        public static BufferedImage getResourceFromID(int id) {
            return loadedResources.get(id);
        }

        public static void unloadResource(int textureID) {
            loadedResources.remove(textureID);
        }
    }

    public static class RenderingUtils {
        public static final GuiRenderer GUI_RENDERER = new GuiRenderer();
    }

    public static class ContextValues {
        public static int WINDOW_WIDTH;
        public static int WINDOW_HEIGHT;

        public static boolean DEBUG_MODE = false;
    }

}
