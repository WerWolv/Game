package com.werwolv.api.modloader;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class ModLoader {

    private List<ModFile> loadedMods = Lists.newArrayList();

    public void loadMod(String path) {
        File file = new File(path);
        URI uri = file.toURI();

        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            addURL.invoke(ClassLoader.getSystemClassLoader(), uri.toURL());
        } catch (NoSuchMethodException | MalformedURLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        ImmutableSet<ClassPath.ClassInfo> set = null;
        try {
            set = ClassPath.from(getClass().getClassLoader()).getAllClasses();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(ClassPath.ClassInfo c : set) {
            try {
                Class<? extends Mod> clazz = (Class<? extends Mod>) c.load();
                if(clazz.isAnnotationPresent(Mod.class)) {
                    Mod mod = clazz.getAnnotation(Mod.class);
                    this.loadedMods.add(new ModFile(clazz, path, mod.modId(), mod.modName(), mod.modVersion()));
                }
            } catch(NoClassDefFoundError e) {

            }
        }
    }

    public List<ModFile> getLoadedMods() {
        return loadedMods;
    }
}