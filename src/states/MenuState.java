package states;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class MenuState extends State {
	private int _levelSelect;

	public MenuState(){
		
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if (E.getKeyCode() == KeyEvent.VK_UP) {
			if(_levelSelect >1){
				_levelSelect --;
			}
		}
		if (E.getKeyCode() == KeyEvent.VK_DOWN) {
			if(_levelSelect <40){
				_levelSelect++;
			}

		}
		if (E.getKeyCode() == KeyEvent.VK_ENTER) {
			setCurrentState(new LevelState(_levelSelect));
		}
//		if (E.getKeyCode() == KeyEvent.VK_RIGHT) {
//			
//		}
	}

	@Override
	public void init() {

	}

	@Override
	public void render(Graphics g) {
		//g.setFont();
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}

