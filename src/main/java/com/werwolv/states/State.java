package com.werwolv.states;

import java.awt.Graphics2D;

public abstract class State {

	public static final State menuState = new MenuState();
	public static final State gameState = new GameState();

	private static State currState = null;
	
	public static State getCurrentState(){
		return currState;
	}
	
	public static void setCurrentState(State state){
		currState = state;
	}
	
	public abstract void render(Graphics2D g);
	
	public abstract void update();
	
}
