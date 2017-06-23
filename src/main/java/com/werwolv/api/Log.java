package com.werwolv.api;

import com.werwolv.main.Game;

public class Log {

    public static void i(String message) {
        System.out.println("[Info] " + message);
    }

    public static void i(String tag, String message) {
        System.out.println("[Info][" + tag + "] " + message);
    }

    public static void d(String message) {
        if(Game.DEBUG_MODE)
            System.out.println("[Debug] " + message);
    }

    public static void d(String tag, String message) {
        if(Game.DEBUG_MODE)
            System.out.println("[Debug][" + tag + "] " + message);
    }

    public static void wtf(String message) {
        System.err.println("[Error] " + message);
    }

    public static void wtf(String tag, String message) {
        System.err.println("[Error][" + tag + "] " + message);
    }

}
