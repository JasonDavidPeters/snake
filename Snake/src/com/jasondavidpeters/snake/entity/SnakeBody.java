package com.jasondavidpeters.snake.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.jasondavidpeters.snake.GameWindow;

public class SnakeBody {

	private double speed;
	private Color c;
	private double x, y;
	private int width, height;

	public SnakeBody(double x, double y, int width, int height, double speed, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;

	}

	public void render(GameWindow gameWindow) {
		Graphics g = gameWindow.getBufferStrategy().getDrawGraphics();
		g.setColor(getColor());
		g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
		g.dispose();
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Color getColor() {
		return this.c;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean hitBounds() {
		if (getX() + (getWidth() / 2) >= GameWindow.WIDTH * GameWindow.SCALE)
			return true;
		else if (getX() + (getWidth() / 2) < 0)
			return true;
		else if (getY() + (getHeight() / 2) >= GameWindow.HEIGHT * GameWindow.SCALE)
			return true;
		else if (getY() + (getHeight() / 2) < 0)
			return true;
		return false;
	}

}
