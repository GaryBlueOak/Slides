package states;

import main.Game;
import main.Run;
import objects.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LevelState extends State {

	private Board _board;
	private int _slideDirection = 0;
	public boolean _first = false;
	public boolean _victory;
	public int _moves;

	public LevelState(int level) {
		_victory = false;
		_moves = 0;
		levelMake(level);
	}
	
	private void levelMake(int level){
		int difficulty;
		int[] blocks;
		switch(level){
		case 1:
			difficulty = 5;
			blocks = new int[difficulty*difficulty];
			blocks[22] = 1;
			blocks[20] = 1;
			blocks[4] = 2;
			blocks[24] = 2;
			levelGenerate(difficulty, blocks);
			break;
		case 2:
			difficulty = 5;
			blocks = new int[difficulty*difficulty];
			blocks[7] = 2;
			blocks[18] = 2;
			blocks[4] = 3;
			blocks[19] = 4;
			blocks[22] = 3;
			blocks[4] = 4;
			blocks[16] = 2;
			blocks[12] = 2;
					
			levelGenerate(difficulty, blocks);
			break;
		case 3:
			difficulty = 5;
			blocks = new int[difficulty*difficulty];
			blocks[22] = 3;
			blocks[20] = 3;
			blocks[4] = 4;
			blocks[24] = 4;
			levelGenerate(difficulty, blocks);
			break;
		}
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if (!isSliding() && !_victory) {
			if (E.getKeyCode() == KeyEvent.VK_UP) {
				_slideDirection = 1;
				_moves ++;
				_first = true;
			}
			if (E.getKeyCode() == KeyEvent.VK_DOWN) {
				_slideDirection = 3;
				_moves ++;
				_first = true;

			}
			if (E.getKeyCode() == KeyEvent.VK_LEFT) {
				_slideDirection = 4;
				_moves ++;
				_first = true;
			}
			if (E.getKeyCode() == KeyEvent.VK_RIGHT) {
				_slideDirection = 2;
				_moves ++;
				_first = true;
			}
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(44,62,80));
		//g.drawString(_board._score + ":Blocks", _board.getLeft() - 20,_board.getUp() - 20);
		//g.drawString(_board._scoreToWin + ":BlocksToWin",_board.getLeft() + 100, _board.getUp() - 20);
		if(!_victory){
			g.setFont(Game.MEDIUM_GAME_FONT);
			g.drawString((_board.getElapsedTime() / 60) % 60 + ":"+ _board.getElapsedTime() % 60, _board.getRight() - 50,_board.getUp() - 20);
			
		}else{
			g.setFont(Game.SMALL_GAME_FONT);
			g.drawString((_board.getTotalTime() / 60) % 60 + ":"+ _board.getTotalTime() % 60, _board.getRight() - 50,_board.getUp() - 20);
			g.drawString("Total Moves Taken:" + _moves, _board.getLeft(),_board.getUp() - 20);
//			g.setFont(new Font(Game.LARGE_GAME_FONT);
			g.drawString("VICTORY", _board.getLeft() + 160,	_board.getUp() - 70);
			
		}
		g.drawRect(_board.getLeft()-1, _board.getUp()-1, _board.getWidth()+1,	_board.getHeight()+1);
		g.setFont(new Font("Arial", Font.PLAIN, 8));
		for (Block block : _board.getBlocks()) {
			g.setColor(block.getColor());
			g.fillRect(block.x, block.y, Block.BLOCK_SIZE, Block.BLOCK_SIZE);
			//g.setColor(Color.BLACK);
			//g.drawString(block.print, block.x + 1, block.x + 15);
		}

	}

	public void levelGenerate(int boardSize,int[] level) {
		_board = new Board(boardSize);
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (int i = 0; i < boardSize*boardSize; i++) {
			if(level[i] != 0){
				blocks.add(new Block(level[i],_board.getLeft()+(i%boardSize)*Block.BLOCK_SIZE,_board.getUp()+(i/boardSize)*Block.BLOCK_SIZE));
			}
		}
		_board.addBlocks(blocks);
	}

	public void slide() {
		if (!_victory) {
			if (_first) {
				for (Block b : _board.getBlocks()) {
					b._sliding = true;
				}
				switch (_slideDirection) {
				case 1:
					_board.sortUp();
					break;
				case 2:
					_board.sortRight();
					break;
				case 3:
					_board.sortDown();
					break;
				case 4:
					_board.sortLeft();
					break;
				}
				;
			}
			if (isSliding()) {
				for (Block b : _board.getBlocks()) {
					switch (_slideDirection) {
					case 1:
						b.slideUp(_board);
						break;
					case 2:
						b.slideRight(_board);
						break;
					case 3:
						b.slideDown(_board);
						break;
					case 4:
						b.slideLeft(_board);
						break;

					}
					;
				}
			}
			if (_first) {
				_first = false;
			}
		}
	}

	public boolean isSliding() {
		for (Block block : _board.getBlocks()) {
			if (block._sliding) {
				return true;
			}
		}
		_slideDirection = 0;
		_board.checkDeletion();
		return false;
	}

	@Override
	public void tick() {
		slide();
		if(_victory == false && _board.victory()){
			_victory = true;
		}

	}
}
