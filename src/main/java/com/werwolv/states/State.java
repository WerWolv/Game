package com.werwolv.states;

import com.werwolv.api.IUpdatable;

import java.awt.Graphics2D;

public abstract class State implements IUpdatable {

	public static final State menuState = new MenuState();
	public static final State gameState = new GameState();

	private static State currState = null;

	public State() {
		this.setUpdateable();
	}
	
	public static State getCurrentState(){
		return currState;
	}
	
	public static void setCurrentState(State state){
		currState = state;
	}

	public abstract void init();

	public abstract void render(Graphics2D g);

}
