package com.werwolv.states;

import java.awt.Color;
import java.awt.Graphics2D;

public class MenuState extends State{

    @Override
    public void init() {

    }

	@Override
	public void update(long delta) {

	}

    @Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(50, 50, 70, 70);
		
	}
}
