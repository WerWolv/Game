package com.werwolv.api.modloader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {

    public void loadMod(String path) {
        try {
            JarFile jar = new JarFile(path);
            Enumeration<JarEntry> e = jar.entries();

            while(e.hasMoreElements())
                System.out.println(e.nextElement());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
