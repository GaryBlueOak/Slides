package main;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import states.*;


public class InputHandler implements KeyListener {
	
	private State _currentState;
	
	public void setCurrentState(State currentState) {
		this._currentState = currentState;
	}

	public void keyPressed(KeyEvent e) {
		_currentState.onKeyPress(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}