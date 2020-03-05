package com.jasondavidpeters.snake.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.jasondavidpeters.snake.GameWindow;
import com.jasondavidpeters.snake.input.Keyboard;

public class Player extends SnakeBody {

	private double speed;

	public Player(int x, int y, int width, int height, int speed, Color c) {
		super(x, y, width, height, speed, c);
		this.speed = speed;
	}

	private void move() {
		if (Keyboard.moveRight) {
			this.setX(getX() + 1 * speed);
		}
		if (Keyboard.moveLeft) {
			this.setX(getX() - 1 * speed);
		}
		if (Keyboard.moveUp) {
			this.setY(getY() - 1 * speed);
		}
		if (Keyboard.moveDown) {
			this.setY(getY() + 1 * speed);
		}
	}

	private void death() {
		// set coordinates to 0,0
		// clear player
		if (hitBounds()) {
			setX((GameWindow.WIDTH * GameWindow.SCALE) / 2);
			setY((GameWindow.HEIGHT * GameWindow.SCALE) / 2);
			Keyboard.moveRight = false;
			Keyboard.moveLeft = false;
			Keyboard.moveDown = false;
			Keyboard.moveUp = false;
		}
	}

	public void tick() {
		move();
		death();
	}

	public void render(GameWindow gameWindow) {
		Graphics g = gameWindow.getBufferStrategy().getDrawGraphics();
		g.setColor(getColor());
		g.fillRect((int) getX(), (int) getY(), getWidth(), getHeight());
		g.dispose();

	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean hitBounds() {
		if (this.getX() + (this.getWidth() / 2) >= GameWindow.WIDTH * GameWindow.SCALE) {
			return true;
		} else if (this.getX() + (this.getWidth() / 2) < 0) {
			return true;
		} else if (this.getY() + (this.getHeight() / 2) >= GameWindow.HEIGHT * GameWindow.SCALE) {
			return true;
		} else if (this.getY() + (this.getHeight() / 2) < 0) {
			return true;
		}
		return false;
	}

}
