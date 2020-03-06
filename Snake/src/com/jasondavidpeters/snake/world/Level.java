package com.jasondavidpeters.snake.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.jasondavidpeters.snake.GameWindow;
import com.jasondavidpeters.snake.entity.Player;
import com.jasondavidpeters.snake.entity.SnakeBody;
import com.jasondavidpeters.snake.entity.SnakeFood;
import com.jasondavidpeters.snake.input.Keyboard;

public class Level {
	Player p;
	SnakeFood sf;
	private Random random;
	private ArrayList<SnakeBody> bodyParts = new ArrayList<SnakeBody>();
	private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	private int ind = 0;
	private int score = 0;
	private double X = 0;
	private double Y = 0;
	private boolean initialization = false;

	public Level() {
		p = new Player((GameWindow.WIDTH * GameWindow.SCALE) / 2, (GameWindow.HEIGHT * GameWindow.SCALE) / 2, 20, 20, 5,
				Color.GREEN);
		random = new Random();
		sf = new SnakeFood(random.nextInt(GameWindow.WIDTH), random.nextInt(GameWindow.HEIGHT), 15, 15, Color.BLUE);
	}

	private void respawn() {
		sf.setX(random.nextInt(GameWindow.WIDTH));
		sf.setY(random.nextInt(GameWindow.HEIGHT));
	}

	private boolean isAlive() {
		if (p.getX() + (p.getWidth() / 2) >= sf.getX() - (sf.getWidth() / 2)
				&& (p.getX() - (p.getWidth() / 2) <= sf.getX() + (sf.getWidth() / 2))
				&& (p.getY() - (p.getHeight() / 2) <= sf.getY() + (sf.getHeight() / 2)
						&& p.getY() + (p.getHeight() / 2) >= sf.getY() - (sf.getHeight() / 2)))
			return false;

		return true;
	}

	private void foodEaten() {
		if (!isAlive()) {
			score++;
			// bodyParts.add((SnakeBody) p);
			respawn();
			bodyParts.add(new SnakeBody(Integer.MAX_VALUE-1,Integer.MAX_VALUE-1,
					p.getWidth(), p.getHeight(), 0,Color.GREEN));
		}
	}

	public void render(GameWindow gameWindow) {
		Graphics g = gameWindow.getBufferStrategy().getDrawGraphics();
		sf.render(gameWindow);
		p.render(gameWindow);
		for (int i = 0; i < bodyParts.size(); i++)
			bodyParts.get(i).render(gameWindow);
		g.drawString("Score: " + score, 50, 50);
		g.dispose();
	}

	class Coordinate {
		double x, y;

		public Coordinate(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

	}
	
	public boolean eatenSelf() { 
		for (int i = 0; i < bodyParts.size(); i++) {
			if (p.getX()-p.getWidth() + (p.getWidth() / 2) >= bodyParts.get(i).getX() - (bodyParts.get(i).getWidth() / 2) // right-side of player
					&& (p.getX()+p.getWidth() - (p.getWidth() / 2) <= bodyParts.get(i).getX() + (bodyParts.get(i).getWidth() / 2)) // left-side of player
					&& (p.getY()+p.getHeight() - (p.getHeight() / 2) <= bodyParts.get(i).getY() + (bodyParts.get(i).getHeight() / 2) // bottom of player
							&& p.getY()-p.getHeight() + (p.getHeight() / 2) >= bodyParts.get(i).getY() // top of player
									- (bodyParts.get(i).getHeight() / 2)))
				return true;
		
	}
		return false;
	}

	public void death() {
		if (p.hitBounds()  || eatenSelf()) {
			p.setX((GameWindow.WIDTH * GameWindow.SCALE) / 2);
			p.setY((GameWindow.HEIGHT * GameWindow.SCALE) / 2);
			Keyboard.moveRight = false;
			Keyboard.moveLeft = false;
			Keyboard.moveDown = false;
			Keyboard.moveUp = false;
			initialization = false;
			coordinates.clear();
			bodyParts.clear();
			score = 0;
			ind = 0;
		}
	}

	public void tick() {
		p.tick();
		death();

		/*
		 * When player moves distance between it's own width or height then we store
		 * coordinates
		 */
		if (!bodyParts.isEmpty() && !initialization) {
			X = p.getX();
			Y = p.getY();
			if (((p.getX() - X) >= p.getWidth())) {
				if (Keyboard.moveRight) {
					Y = p.getY();
					X = p.getX() - p.getWidth();
				}
			}
			if ((p.getY() - Y) >= p.getHeight()) {

				if (Keyboard.moveDown) {
					Y = p.getY() - p.getHeight();
					X = p.getX();
				}
			}
			if ((X - p.getX()) >= p.getWidth()) {
				if (Keyboard.moveLeft) {
					Y = p.getY();
					X = p.getX() + p.getWidth();
				}
			}
			if ((Y - p.getY()) >= p.getHeight()) {
				if (Keyboard.moveUp) {
					Y = p.getY() + p.getHeight();
					X = p.getX();
				}
			}
			initialization = true;
		}
		foodEaten();
		if (!bodyParts.isEmpty())
			if (coordinates.size() >= bodyParts.size()) {
				if (((p.getX() - X) >= p.getWidth())) {
					if (Keyboard.moveRight) {
						Y = p.getY();
						X = p.getX() - p.getWidth();
						if (ind != 0)
							if (bodyParts.size() > 1) {
								X = (p.getX() - (p.getWidth()));
							}
					}
				}
				if ((p.getY() - Y) >= p.getHeight()) {
					if (Keyboard.moveDown) {
						Y = p.getY() - p.getHeight();
						X = p.getX();
						if (ind != 0)
							if (bodyParts.size() > 1)
								Y = (p.getY() - (p.getHeight()));
					}
				}
				if ((X - p.getX()) >= p.getWidth()) {
					if (Keyboard.moveLeft) {
						Y = p.getY();
						X = p.getX() + p.getWidth();
						if (ind != 0)
							if (bodyParts.size() > 1)
								X = (p.getX() + (p.getWidth()));
					}
				}
				if ((Y - p.getY()) >= p.getHeight()) {
					if (Keyboard.moveUp) {
						Y = p.getY() + p.getHeight();
						X = p.getX();
						if (ind != 0)
							if (bodyParts.size() > 1)
								Y = (p.getY() + (p.getHeight()));
					}
				}
				coordinates.set(ind, new Coordinate(X, Y));
				ind++;
				if (ind >= coordinates.size())
					ind = 0;
			} else {
				coordinates.add(new Coordinate(X, Y));
			}

		for (int i = 0; i < bodyParts.size(); i++) {
			bodyParts.get(i).setX(coordinates.get(i).getX());
			bodyParts.get(i).setY(coordinates.get(i).getY());
		}

	}

}
