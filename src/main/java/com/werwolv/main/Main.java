package com.werwolv.main;

import com.werwolv.api.API;

import java.io.File;
import java.util.Arrays;

public class Main {

	public static final File MODS_FOLDER = new File("mods");

	public static void main(String[] args) {

	    for(String arg : args) {
	        switch(arg) {
                case "-m":
                case "-mods":
                    API.ContextValues.LOAD_MODS = true;
                    break;
                case "-f":
                case "-fullscreen":
                    API.ContextValues.FULL_SCREEN = true;
                    break;
                case "-d":
                case "-debug":
                    API.ContextValues.DEBUG_MODE = true;
                    break;
            }
        }

        if(API.ContextValues.LOAD_MODS) {
            if (!MODS_FOLDER.exists())
                MODS_FOLDER.mkdirs();

            Arrays.stream(MODS_FOLDER.listFiles()).filter(mod -> mod.getName().endsWith(".jar")).forEach(mod -> API.MOD_LOADER.loadMod(mod.getAbsolutePath()));
        }

		Game.INSTANCE.start();
	}
	
}
