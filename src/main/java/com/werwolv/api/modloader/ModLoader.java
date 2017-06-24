package com.werwolv.api.modloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class ModLoader {

    public void loadMod(String path) {
        File file = new File(path);
        URI uri = file.toURI();

        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            addURL.setAccessible(true);
            addURL.invoke(ClassLoader.getSystemClassLoader(), new Object[]{uri.toURL()});
        } catch (NoSuchMethodException | MalformedURLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}