package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;
import main.Run;

public class LevelSelectorState extends State {
	private int _levelSelect = 1;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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
	public void render(Graphics g) {
		g.setFont(Game.SMALL_GAME_FONT);
		g.setColor(new Color(26, 188, 156));
		g.fillRect(Run.GAME_WIDTH/8, 0, Run.GAME_WIDTH/10, Run.GAME_HEIGHT);
		g.setColor(new Color(44,62,80));
		g.drawString(_levelSelect + "",Run.GAME_WIDTH/8 + ((Run.GAME_WIDTH/10)/3),Run.GAME_HEIGHT/2);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
