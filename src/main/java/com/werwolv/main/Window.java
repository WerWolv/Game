package com.werwolv.main;

import com.werwolv.api.API;
import com.werwolv.api.event.input.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	private long window;

	private double mouseX, mouseY;

    private static boolean[] pressedKeys = new boolean[1024];
    private boolean hasBeenResized = false;

    public Window() {
        API.ContextValues.WINDOW_WIDTH = 1080;
        API.ContextValues.WINDOW_HEIGHT = 720;
    }


    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys[keyCode];
    }

    public boolean hasBeenResized() {
        return hasBeenResized;
    }

    public void setResized(boolean hasBeenResized) {
        this.hasBeenResized = hasBeenResized;
    }

    public void createWindow(boolean fullscreen) {
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW!");


        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(API.ContextValues.WINDOW_WIDTH, API.ContextValues.WINDOW_HEIGHT, "Game", fullscreen ? glfwGetPrimaryMonitor() : 0, 0);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(window, (vidMode.width() - pWidth.get(0)) / 2, (vidMode.height() - pHeight.get(0)) / 2);
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        glfwSetWindowSizeCallback(window, (window, width, height) ->{
            API.ContextValues.WINDOW_WIDTH = width;
            API.ContextValues.WINDOW_HEIGHT = height;
            hasBeenResized = true;
        });

        glfwSetKeyCallback(window, (window, key, scanCode, action, mods) -> {
            if(key == -1) return;
                switch (action) {
                    case GLFW_PRESS:
                        if (!pressedKeys[key])
                            API.EVENT_BUS.postEvent(new KeyTypedEvent(key));

                        pressedKeys[key] = true;
                        API.EVENT_BUS.postEvent(new KeyPressedEvent(key));
                        break;
                    case GLFW_RELEASE:
                        pressedKeys[key] = false;
                        API.EVENT_BUS.postEvent(new KeyReleasedEvent(key));
                        break;
                    case GLFW_REPEAT:
                        API.EVENT_BUS.postEvent(new KeyHeldEvent(key));
                        break;
                }
        });

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            switch (action) {
                case GLFW_PRESS:
                    API.EVENT_BUS.postEvent(new MouseClickedEvent(EnumMouseButton.getButtonFromID(button), this.mouseX, this.mouseY));
                    break;
                case GLFW_RELEASE:
                    API.EVENT_BUS.postEvent(new MouseReleasedEvent(EnumMouseButton.getButtonFromID(button), this.mouseX, this.mouseY));
                    break;
            }
        });

        glfwSetCursorPosCallback(window, (window, xPos, yPos) -> {
            this.mouseX = xPos;
            this.mouseY = yPos;
            API.EVENT_BUS.postEvent(new MouseMovedEvent(xPos, yPos));
        });
    }

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public long getWindow() {
		return window;
	}
}
