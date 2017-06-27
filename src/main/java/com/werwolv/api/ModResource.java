package com.werwolv.api;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ModResource {

    private String path;
    private BufferedImage image;

    public ModResource(String path) {
        this.path = path;

        try {
            this.image = ImageIO.read(ClassLoader.getSystemClassLoader().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public BufferedImage getImage() {
        return image;
    }
}
