package com.werwolv.api;

import com.werwolv.api.eventbus.EventBus;
import com.werwolv.api.modloader.ModLoader;
import com.werwolv.api.resource.Texture;
import com.werwolv.item.Item;
import com.werwolv.renderer.GuiRenderer;
import com.werwolv.tile.Tile;

import java.util.HashMap;
import java.util.Map;

public class API {

    public static final EventBus EVENT_BUS = new EventBus();
    public static final ModLoader MOD_LOADER = new ModLoader();

    public static class GameRegistry {
        private static Map<Integer, Tile> registeredTiles = new HashMap<>();
        private static Map<Integer, Item> registeredItems = new HashMap<>();

        /////TILES/////

        public static void registerTile(Tile tile) {
            if(registeredTiles.containsKey(tile.getTileID())) {
                Log.wtf("GameRegistry", "Tile with ID " + tile.getTileID() + " is already registered!");
                return;
            }

            registeredTiles.put(tile.getTileID(), tile);
        }

        public static void unregisterTile(Tile tile) {
            if(!registeredTiles.containsKey(tile.getTileID())) {
                Log.wtf("GameRegistry", "Tile with ID " + tile.getTileID() + " isn't registered! Can't unregister!");
                return;
            }

                registeredTiles.remove(tile.getTileID());
        }

        public static void unregisterTile(int tileID) {
            if(!registeredTiles.containsKey(tileID)) {
                Log.wtf("GameRegistry", "Tile with ID " + tileID + " isn't registered! Can't unregister!");
                return;
            }

            registeredTiles.remove(tileID);
        }

        public static Tile getTileFromID(int id) {
            return registeredTiles.get(id);
        }

        /////ITEMS//////

        public static void registerItem(Item item) {
            if(registeredItems.containsKey(item.getItemID())) {
                Log.wtf("GameRegistry", "Item with ID " + item.getItemID() + " is already registered!");
                return;
            }

            registeredItems.put(item.getItemID(), item);
        }

        public static void unregisterItem(Item item) {
            if(!registeredItems.containsKey(item.getItemID())) {
                Log.wtf("GameRegistry", "Item with ID " + item.getItemID() + " isn't registered! Can't unregister!");
                return;
            }

            registeredItems.remove(item.getItemID());
        }

        public static void unregisterItem(int itemID) {
            if(!registeredItems.containsKey(itemID)) {
                Log.wtf("GameRegistry", "Item with ID " + itemID + " isn't registered! Can't unregister!");
                return;
            }

            registeredItems.remove(itemID);
        }

        public static Item getItemFromID(int id) {
            return registeredItems.get(id);
        }
    }

    public static class ResourceRegistry {
        private static Map<Integer, Texture> loadedResources = new HashMap<>();

        private static int registeredTextures = 0;

        public static int registerResource(String path) {
            int resourceID;

            if(ClassLoader.getSystemClassLoader().getResource(path) == null) {
                Log.wtf("ResourceRegistry", "Cannot load file " + path);
                return -1;
            }

            registeredTextures++;

            for(resourceID = 0; resourceID < registeredTextures; resourceID++) {
                if (loadedResources.get(resourceID) == null) {
                    loadedResources.put(resourceID, new Texture(path));
                    break;
                }
            }

            Log.d("ResourceRegistry", "Loaded resource " + path + " as resource ID " + resourceID);

            return resourceID;
        }

        public static Texture getResourceFromID(int id) {
            return loadedResources.get(id);
        }

        public static void unloadResource(int resourceID) {
            loadedResources.remove(resourceID);
        }
    }

    public static class RenderingUtils {
        public static final GuiRenderer GUI_RENDERER = new GuiRenderer();
    }

    public static class ContextValues {
        public static int WINDOW_WIDTH;
        public static int WINDOW_HEIGHT;
        public static int MONITOR_WIDTH;
        public static int MONITOR_HEIGHT;

        public static int DRAGGED_ITEM_SIZE = 64;

        public static boolean DEBUG_MODE = false;
        public static boolean LOAD_MODS = false;
        public static boolean FULL_SCREEN = false;
    }

}
