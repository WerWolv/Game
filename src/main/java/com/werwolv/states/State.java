package com.werwolv.states;

import com.werwolv.api.IUpdatable;
import com.werwolv.engine.renderer.Camera;

public abstract class State implements IUpdatable {

	public static final State menuState = new MenuState();
	public static final State gameState = new GameState();

	public static double mouseX, mouseY;

	private Camera CAMERA = new Camera();

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

	public abstract void render();

    public Camera getCamera() {
        return CAMERA;
    }
}
