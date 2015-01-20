package states;


import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Run;

public abstract class State {
	
	public abstract void init();
	
	public abstract void onKeyPress(KeyEvent E);
	
	public void setCurrentState(State newState) {
		Run.sGame.setCurrentState(newState);
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	

}