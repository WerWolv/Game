package com.werwolv.main;

import com.werwolv.api.API;

public class Main {

	public static void main(String[] args) {
		API.MOD_LOADER.loadMod("D:\\__Development\\Java\\GameMod\\out\\artifacts\\Game_main_jar\\Game_main.jar");

		Game.INSTANCE.start();
	}
	
}
