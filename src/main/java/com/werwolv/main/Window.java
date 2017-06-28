package com.werwolv.main;

import com.werwolv.handler.KeyHandler;
import com.werwolv.handler.MouseHandler;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window{

	private JFrame frame;
	private Canvas canvas;

	private int width, height;	
	private String title;

	public Window(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		init();
	}

	public void init(){

	    KeyHandler keyHandler = new KeyHandler();
	    MouseHandler mouseHandler = new MouseHandler();

		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
        frame.addKeyListener(keyHandler);
        frame.addMouseListener(mouseHandler);
        frame.addMouseMotionListener(mouseHandler);
        frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas(){
		return canvas;
	}

	public JFrame getFrame(){
		return frame;
	}

}
