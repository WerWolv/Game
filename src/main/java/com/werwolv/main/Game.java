package com.werwolv.main;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import com.werwolv.api.API;
import com.werwolv.api.IUpdatable;
import com.werwolv.api.event.init.InitializationEvent;
import com.werwolv.api.event.init.PostInitializationEvent;
import com.werwolv.api.event.init.PreInitializationEvent;
import com.werwolv.states.GameState;
import com.werwolv.states.State;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Game implements Runnable{

	public static final Game INSTANCE = new Game("Game", 1080, 720);

	private BufferStrategy bs;
	private Graphics2D g;
	private Thread thread;
	private boolean running = false;

    private String title;
	private Window window;

	private Game(String title, int width, int height){
		this.title = title;

        API.ContextValues.WINDOW_WIDTH = width;
        API.ContextValues.WINDOW_HEIGHT = height;
	}
	
	public void init(){
	    window = new Window();
	    window.setSize(API.ContextValues.WINDOW_WIDTH, API.ContextValues.WINDOW_HEIGHT);

	    System.out.println("LWJGL version " + Version.getVersion());
		GLFWErrorCallback.createPrint(System.err).set();

        window.createWindow(false);

        GL.createCapabilities();

        State.setCurrentState(State.gameState);


		API.EVENT_BUS.registerEventHandlers();

        API.EVENT_BUS.postEvent(new PreInitializationEvent());
        API.EVENT_BUS.postEvent(new InitializationEvent());
        API.EVENT_BUS.postEvent(new PostInitializationEvent());

        API.EVENT_BUS.processEvents();

        State.getCurrentState().init();

	}
	
	public void render(){
		/*bs = window.getCanvas().getBufferStrategy();
		if(bs == null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		API.RenderingUtils.GUI_RENDERER.setGraphics(g);

		g.clearRect(0, 0, width, height);*/
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        if(State.getCurrentState() != null)
            State.getCurrentState().render();

        glfwSwapBuffers(window.getWindow());
        glfwPollEvents();
		
		/*bs.show();
		g.dispose();*/

	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		init();

		int tps = 250;
		double timePerTick = 1E9 / tps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while(!window.shouldClose()){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
                API.EVENT_BUS.processEvents();

				for(IUpdatable updatable : IUpdatable.updateableInstances)
				    updatable.update((int)delta);

				delta--;
			}
			if(timer >= 1E9) timer = 0;

			render();
		}

        glfwFreeCallbacks(window.getWindow());
		glfwDestroyWindow(window.getWindow());
		glfwTerminate();
		glfwSetErrorCallback(null).free();
        System.exit(0);
    }

    public Window getWindow() {
        return window;
    }
}
