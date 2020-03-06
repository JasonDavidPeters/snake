package com.jasondavidpeters.snake.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.jasondavidpeters.snake.GameWindow;

public class SnakeFood extends SnakeBody {
	
	private boolean isAlive;
	
	public SnakeFood(double x, double y, int width, int height, Color c) {
		super(x, y, height, height, 0, c);
	}
	
	public void render(GameWindow gameWindow) {
		Graphics g = gameWindow.getBufferStrategy().getDrawGraphics();
		g.setColor(getColor());
		g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
		g.dispose();
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}

}
