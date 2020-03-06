package com.jasondavidpeters.snake;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import com.jasondavidpeters.snake.input.Keyboard;

public class GameWindow extends Canvas {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 340;
	public static final int SCALE = 2;
	public static final String GAME_NAME = "Snake";
	
	private Keyboard kb;
	
//	private Keyboard kb;
//	private Player p;

	private static final long serialVersionUID = 1L;

	public GameWindow() {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(this);
		frame.setTitle(GAME_NAME);
		frame.pack();
		frame.setLocationRelativeTo(null);
		requestFocus();
		kb = new Keyboard();
		addKeyListener(kb);

	}

	public void paint(Graphics g) {

	}

}
