package com.werwolv.api;

import com.werwolv.api.event.quest.QuestFinishedEvent;
import com.werwolv.api.event.quest.QuestTaskFinishedEvent;
import com.werwolv.api.eventbus.EventBus;
import com.werwolv.api.modloader.ModLoader;
import com.werwolv.engine.renderer.ModelRenderer;
import com.werwolv.engine.resource.Texture;
import com.werwolv.entities.EntityPlayer;
import com.werwolv.item.Item;
import com.werwolv.quest.Quest;
import com.werwolv.engine.renderer.GuiRenderer;
import com.werwolv.tile.Tile;
import com.werwolv.world.World;

import javax.xml.soap.Text;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class API {

    public static final EventBus EVENT_BUS = new EventBus();
    public static final ModLoader MOD_LOADER = new ModLoader();

    public static final World theWorld = new World();
    public static final EntityPlayer thePlayer = new EntityPlayer(theWorld, 0, 0);

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
        private static Map<Integer, File> loadedResources = new HashMap<>();
        private static Map<Integer, Texture> loadedTextures = new HashMap<>();

        private static int registeredTextures = 0;

        public static int registerResource(String path) {
            int resourceID;

            if(ClassLoader.getSystemClassLoader().getResource(path) == null) {
                Log.wtf("ResourceRegistry", "Cannot load file " + path);
                return -1;
            }

            registeredTextures++;

            for(resourceID = 0; resourceID < registeredTextures; resourceID++) {
                if (loadedResources.get(resourceID) == null && !path.endsWith(".png")) {
                        loadedResources.put(resourceID, new File(path));
                    break;
                }

                if (loadedTextures.get(resourceID) == null && path.endsWith(".png")) {
                    loadedTextures.put(resourceID, new Texture(path));
                    break;
                }
            }

            Log.d("ResourceRegistry", "Loaded resource " + path + " as resource ID " + resourceID);

            return resourceID;
        }

        public static File getResourceFromID(int id) {
            return loadedResources.get(id);
        }

        public static Texture getTextureFromID(int id) {
            return loadedTextures.get(id);
        }

        public static void unloadResource(int resourceID) {
            loadedResources.remove(resourceID);
        }
    }

    /**
     * The QuestingApi. In there are all methods used to add and manage Quests.
     */
    public static class QuestingApi {
        private static Map<String, Quest> registeredQuests = new HashMap<>();

        /**
         * Adds a new quest to the game.
         * @param questName the name of the quest. This is an identifier so please use a constant String for this
         * @param quest the instance of the quest to be registered
         */
        public static void registerQuest(String questName, Quest quest) {
            registeredQuests.put(questName, quest);
        }

        /**
         * If a new quest was added to the game and the player doesn't have it in his list already, it adds them.
         */
        public static void addQuestsToPlayer() {
            registeredQuests.forEach((k, o) -> thePlayer.getPlayerData().notStartedQuests.putIfAbsent(k, o));
        }

        /**
         * Gets a quest by its name
         * @param questName the name of the quest
         * @return the quest object associated with this name
         */
        public static Quest getQuestByName(String questName) {
            Quest quest = null;

            quest = thePlayer.getPlayerData().notStartedQuests.get(questName);
            if (quest != null) return quest;

            quest = thePlayer.getPlayerData().startedQuests.get(questName);
            if (quest != null) return quest;

            quest = thePlayer.getPlayerData().finishedQuests.get(questName);

            return quest;
        }

        /**
         * Checks if the quest specified by the quest name was started by the player
         * @param questName the name of the quest
         * @return true if the quest was started
         */
        public static boolean isQuestStarted(String questName) {
            return thePlayer.getPlayerData().startedQuests.containsKey(questName);
        }

        /**
         * Checks if the quest specified by the quest name was finished by the player
         * @param questName the name of the quest
         * @return true if the quest was finished
         */
        public static boolean isQuestFinished(String questName) {
            return thePlayer.getPlayerData().finishedQuests.containsKey(questName);
        }

        /**
         * Finishes the passed quest task if the quest task name maches the name of the current quest task.
         * @param questName the name of the quest.
         * @param questTaskName the name of the quest task.
         */
        public static void finishQuestTask(String questName, String questTaskName) {
            if (!thePlayer.getPlayerData().startedQuests.containsKey(questName))
                return;

            if (!getQuestByName(questName).doesQuestHasTask(questTaskName)) {
                Log.i("QuestingApi", "Name of task isn't available in this quest. Are you sure both are written correctly?");
                return;
            }

            if (thePlayer.getPlayerData().startedQuests.get(questName).getCurrentTask().getTaskName().equals(questTaskName)) {
                API.EVENT_BUS.postEvent(new QuestTaskFinishedEvent(getQuestByName(questName), thePlayer.getPlayerData().startedQuests.get(questName).finishCurrentTask()));
            }

            if (thePlayer.getPlayerData().startedQuests.get(questName).getCurrentTask() == null) {
                finishQuest(questName);
                API.EVENT_BUS.postEvent(new QuestFinishedEvent(getQuestByName(questName)));
                return;
            }
        }

        /**
         * Starts the passed quest if it wasn't started already.
         * @param questName the quest to start
         */
        public static void startQuest(String questName) {

            if (thePlayer.getPlayerData().finishedQuests.containsKey(questName) ||
                    thePlayer.getPlayerData().startedQuests.containsKey(questName)) {
                Log.i("QuestingApi", "No need to start quest. It is already started or finished!");
                return;
            }

            if (!thePlayer.getPlayerData().finishedQuests.values().containsAll(getQuestByName(questName).getDependencies())) {
                Log.i("QuestingApi", "Quest cannot be started because the player's missing some dependencies!");
                return;
            }

            if (!thePlayer.getPlayerData().notStartedQuests.containsKey(questName)) {
                Log.i("QuestingApi", "Quest cannot be started because it is already started or finished");
                return;
            }

            thePlayer.getPlayerData().startedQuests.put(questName, thePlayer.getPlayerData().notStartedQuests.remove(questName));
        }

        /**
         * Finishes the passed quest if it wasn't finished already.
         * @param questName the quest to finish
         */
        public static void finishQuest(String questName) {
            if (!thePlayer.getPlayerData().startedQuests.containsKey(questName)) {
                Log.i("QuestingApi", "Cannot finish quest because it is already finished or not yet started");
                return;
            }

            thePlayer.getPlayerData().finishedQuests.put(questName, thePlayer.getPlayerData().startedQuests.remove(questName));
        }
    }

    public static class RenderingUtils {
        public static final ModelRenderer MODEL_RENDERER = new ModelRenderer();
        public static final GuiRenderer GUI_RENDERER = new GuiRenderer();
    }

    public static class ContextValues {
        public static int WINDOW_WIDTH;
        public static int WINDOW_HEIGHT;
        public static int MONITOR_WIDTH;
        public static int MONITOR_HEIGHT;

        public static float WORLD_SCALE = 50.0F;

        public static int DRAGGED_ITEM_SIZE = 64;

        public static boolean DEBUG_MODE = false;
        public static boolean LOAD_MODS = false;
        public static boolean FULL_SCREEN = false;
    }

}
