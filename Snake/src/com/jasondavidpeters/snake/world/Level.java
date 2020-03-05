package com.jasondavidpeters.snake.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.jasondavidpeters.snake.GameWindow;
import com.jasondavidpeters.snake.entity.Player;
import com.jasondavidpeters.snake.entity.SnakeBody;
import com.jasondavidpeters.snake.entity.SnakeFood;
import com.jasondavidpeters.snake.input.Keyboard;

public class Level {
	/*
	 * Generate snake food Load in player
	 */
	Player p;
	SnakeFood sf;
	private Random random;
	private ArrayList<SnakeBody> bodyParts = new ArrayList<SnakeBody>();
	private int ticks;
	private boolean enableFollowing;

	private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

	public Level() {
		p = new Player((GameWindow.WIDTH * GameWindow.SCALE) / 2, (GameWindow.HEIGHT * GameWindow.SCALE) / 2, 20, 20, 5,
				Color.GREEN);
		random = new Random();
		sf = new SnakeFood(random.nextInt(GameWindow.WIDTH), random.nextInt(GameWindow.HEIGHT), 30, 30, Color.BLUE);
	}

	private void respawn() {
		sf.setX(random.nextInt(GameWindow.WIDTH));
		sf.setY(random.nextInt(GameWindow.HEIGHT));
	}

	private boolean isAlive() {

		if ((p.getX() - (p.getWidth() / 2)) >= (sf.getX() - (sf.getWidth() / 2))
				&& (p.getX() + (p.getWidth()) / 2) <= (sf.getX() + (sf.getWidth()))
				&& (p.getY() - (p.getHeight() / 2)) >= (sf.getY() - (sf.getHeight() / 2))
				&& (p.getY() + (p.getHeight()) / 2) <= (sf.getY() + (sf.getHeight()))) {
			
			return false;
		}
		return true;
	}

	private void foodEaten() {
		if (!isAlive()) {
			// bodyParts.add((SnakeBody) p);
			respawn();
			if (Keyboard.moveRight)
				bodyParts.add(new SnakeBody((p.getX() - 15), p.getY(), p.getWidth(), p.getHeight(), 0, p.getColor()));
			if (Keyboard.moveLeft)
				bodyParts.add(new SnakeBody((p.getX() + 15), p.getY(), p.getWidth(), p.getHeight(), 0, p.getColor()));
			if (Keyboard.moveUp)
				bodyParts.add(new SnakeBody(p.getX(), (p.getY() + 15), p.getWidth(), p.getHeight(), 0, p.getColor()));
			if (Keyboard.moveDown)
				bodyParts.add(new SnakeBody(p.getX(), (p.getY() - 15), p.getWidth(), p.getHeight(), 0, p.getColor()));

			// coordinates.add(new Coordinate(0, 0));
		} // kill entity and respawn elsewhere.
	}

	public void render(GameWindow gameWindow) {
		sf.render(gameWindow);
		p.render(gameWindow);
		for (int i = 0; i < bodyParts.size(); i++)
			bodyParts.get(i).render(gameWindow);
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

	int ind = 0;

	public void tick() {
		if (p.hitBounds()) System.out.println("hit bounds");
		double X = p.getX();
		double Y = p.getY();
		p.tick();
		foodEaten();
		if (!bodyParts.isEmpty())
			if (coordinates.size() >= bodyParts.size()) {
				coordinates.set(ind, new Coordinate(X, Y));
				ind++;
			} else {
				coordinates.add(new Coordinate(X, Y));
			}
		if (ind >= coordinates.size())
			ind = 0;
		for (int i = 0; i < bodyParts.size(); i++) {
			bodyParts.get(i).setY(coordinates.get(i).getY());
			bodyParts.get(i).setX(coordinates.get(i).getX());
		}
//		if (p.hitBounds()) {
//			for (int i = 0; i <coordinates.size();i++)coordinates.remove(i);
//			for (int i = 0; i <bodyParts.size();i++)bodyParts.remove(i);
//		}
		ticks++;
	}

}
