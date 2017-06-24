package com.werwolv.main;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.Enumeration;

import com.werwolv.api.API;
import com.werwolv.states.State;

public class Game implements Runnable{

	public static final Game INSTANCE = new Game("Game", 1080, 720);
    public static final boolean DEBUG_MODE = false;


	private BufferStrategy bs;
	private Graphics2D g;
	private Thread thread;
	private boolean running = false;
	
	public String title;
	public int width, height;
	private Window window;
		
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;

		try {
            Enumeration<URL> e = getClass().getClassLoader().getResources("");
            while(e.hasMoreElements()) {
                System.out.println(e.nextElement());
            }
        } catch (Exception e){}
	}
	
	public void init(){
		State.setCurrentState(State.gameState);
		window = new Window(title, width, height);
		API.EVENT_BUS.registerEventHandlers();
	}
	
	public void update(){
		if(State.getCurrentState() != null)
			State.getCurrentState().update();

        API.EVENT_BUS.processEvents();
    }
	
	public void render(){
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		if(State.getCurrentState() != null)
			State.getCurrentState().render(g);
		
		bs.show();
		g.dispose();
		
	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
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
		double timePerTick = 1000000000 / tps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				update();
				delta--;
			}
			if(timer >= 1000000000){
				timer = 0;
			}
			render();
		}

		stop();

	}
	
}
