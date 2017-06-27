package com.werwolv.main;

import com.werwolv.api.API;

import java.io.File;
import java.util.Arrays;

public class Main {

	public static final File MODS_FOLDER = new File("mods");

	public static void main(String[] args) {
		if(!MODS_FOLDER.exists())
			MODS_FOLDER.mkdirs();

		Arrays.asList(MODS_FOLDER.listFiles()).stream().filter(mod -> mod.getName().endsWith(".jar")).forEach(mod -> API.MOD_LOADER.loadMod(mod.getAbsolutePath()));
		Arrays.asList(MODS_FOLDER.listFiles()).stream().filter(mod -> mod.getName().endsWith(".jar")).forEach(mod -> API.MOD_LOADER.extractResources(mod.getAbsolutePath()));


		Game.INSTANCE.start();
	}
	
}
