package com.werwolv.api.resource;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture {

    private int id;
    private int width;
    private int height;

    public Texture(String path) {
        BufferedImage image;

        try {
            image = ImageIO.read(ClassLoader.getSystemClassLoader().getResource(path));
            width = image.getWidth();
            height = image.getHeight();

            int[] pixels_raw;
            pixels_raw = image.getRGB(0, 0, width, height, null, 0, width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

            for(int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int pixel = pixels_raw[x * width + y];
                    pixels.put((byte)((pixel >> 16) & 0xFF));
                    pixels.put((byte)((pixel >> 8 ) & 0xFF));
                    pixels.put((byte)((pixel      ) & 0xFF));
                    pixels.put((byte)((pixel >> 24) & 0xFF));
                }
            }

            pixels.flip();

            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(int sampler) {
        if(sampler < 0 && sampler > 31) return;

        glActiveTexture(GL_TEXTURE0 + sampler);
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

}
