package com.jasondavidpeters.snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	// directions
	public static boolean moveLeft, moveRight, moveDown, moveUp;

	public Keyboard() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) { // MOVERIGHT
			moveRight=true;
			moveDown= moveLeft= moveUp=false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			moveLeft=true;
			moveDown= moveRight= moveUp=false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			moveDown=true;
			moveRight= moveLeft= moveUp=false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			moveUp=true;
			moveRight= moveLeft= moveDown=false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
