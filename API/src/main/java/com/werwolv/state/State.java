package com.werwolv.state;

import com.werwolv.api.IUpdatable;
import com.werwolv.engine.renderer.Camera;

import java.util.HashMap;
import java.util.Map;

public abstract class State implements IUpdatable {

    private static final Map<String, State> states = new HashMap<>();

    private Camera CAMERA = new Camera();

	private static State currState = null;

	public State(String stateName) {
		this.setUpdateable();
		states.put(stateName, this);
	}
	
	public static State getCurrentState(){
		return currState;
	}
	
	public static void setCurrentState(String stateName){
		currState = states.get(stateName);
		currState.init();
	}

	public abstract void init();

	public abstract void deinit();

	public abstract void render();

    public Camera getCamera() {
        return CAMERA;
    }
}
