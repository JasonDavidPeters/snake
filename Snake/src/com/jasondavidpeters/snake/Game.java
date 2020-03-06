package com.jasondavidpeters.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.jasondavidpeters.snake.world.Level;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;
	private GameWindow gameWindow;
	private Level level;
	
	public Game() {
		level = new Level();
		gameWindow = new GameWindow();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.run();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long beforeTime = System.nanoTime();
		double passedTime = 0;
		double ns = 1000000000.0 / 60.0;
		int ticks = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long nowTime = System.nanoTime();
			passedTime += (nowTime - beforeTime) / ns;
			beforeTime = nowTime;

			if (passedTime >= 1) {
				ticks++;
				tick();
				passedTime -= 1;
			}
			if ((System.currentTimeMillis() - timer) >= 1000) {
				System.out.println("Ticks: " + ticks + " FPS: " + frames);
				frames = 0;
				ticks = 0;
				timer = System.currentTimeMillis();
			}
			frames++;
			render();
		}
	}

	private void tick() {
		level.tick();

	}
	private void render() {
		BufferStrategy bs = gameWindow.getBufferStrategy();
		if (bs == null) {
			gameWindow.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, GameWindow.WIDTH*GameWindow.SCALE, GameWindow.HEIGHT*GameWindow.SCALE);
		level.render(gameWindow);
		bs.show();
		g.dispose();
	}

}
