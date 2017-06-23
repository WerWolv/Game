package com.werwolv.states;

import java.awt.Color;
import java.awt.Graphics2D;

public class MenuState extends State{

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(50, 50, 70, 70);
		
	}

	@Override
	public void update() {
		
	}

}
