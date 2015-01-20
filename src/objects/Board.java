package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.Run;

public class Board {
	
	private int _xLeft;
	private int _xRight;
	private int _yUp;
	private int _yDown;
	private ArrayList<Block> _blocks;
	public int _score;
	public int _scoreToWin;
	public long _startTime;
	public long _endTime;
	
	public Board(int size){
		
		 int sizeBoard = size*Block.BLOCK_SIZE;
		_xLeft = (Run.GAME_WIDTH/2) - (sizeBoard/2);
		_yUp = (Run.GAME_HEIGHT/2)- (sizeBoard/2);
		_xRight = _xLeft + (size * Block.BLOCK_SIZE);
		_yDown = _yUp + (size * Block.BLOCK_SIZE);
		
	}
	
	public int getUp(){
		return _yUp;
	}
	
	public boolean victory(){
		if(_score == _scoreToWin){
			_endTime = getElapsedTime();
			return true;
		}
		return false;
	}
	
	public long getTotalTime(){
		return _endTime;
	}
	
	public long getElapsedTime(){
		return (System.currentTimeMillis()-_startTime)/1000;
	}
	
	
	public int getDown(){
		return _yDown;
	}
	
	public int getLeft(){
		return _xLeft;
	}
	
	public void setBlocks(ArrayList<Block> blocks){
		_blocks = blocks;
	}
	
	public int getRight(){
		return _xRight;
	}
	
	public ArrayList<Block> getBlocks(){
		return _blocks;
	}
	
	public int getWidth(){
		return _xRight - _xLeft;
	}
	
	public int getHeight(){
		return _yDown - _yUp;
	}
	
	public void generateScoreToWin(){
		int score = 0;
		ArrayList<Integer> colorIndex = new ArrayList<Integer>();
		 boolean found = false;
		for(Block b:_blocks){
			found = false;
			for(Integer i: colorIndex){
				if(i == b.getColorIndex()){
					found = true;
				}
			}
			if(!found){
				score ++;
				colorIndex.add(b.getColorIndex());
			}
		}
		_scoreToWin = score;
	}
	
	public void addBlocks(ArrayList<Block> blocks){
		_blocks = blocks;
		generateScoreToWin();
		_score = _blocks.size();
		start();
	}
	
	public void start(){
		_startTime = System.currentTimeMillis();
	}
	
	public void checkDeletion(){
		boolean found = false;
		ArrayList<int[]> cord = new ArrayList<int[]>();
		int score = 0;
		ArrayList<Block> toDelete = new ArrayList<Block>();
		for(Block b:_blocks){
			found = false;
			for(int[] cor: cord){
				if(cor[0] == b.x && cor[1] == b.y){
					score ++;
					found = true;
				}
			}
			if(!found){
				int[] temp = new int[2];
				temp[0] = b.x;
				temp[1] = b.y;
				cord.add(temp);
			}
		}
		
			_score = _blocks.size()-score;
	//		_blocks.removeAll(toDelete);
	}

	
	
	
	public void sortRight(){
		Collections.sort(_blocks,new Comparator<Block>(){
			   public int compare(Block a,Block b) {
				   return a.x < b.x ? 1 : a.x == b.x ? 0 : -1;
			     }
			 });
	}
	
	public void sortLeft(){
		Collections.sort(_blocks,new Comparator<Block>(){
			   public int compare(Block a,Block b) {
				   return a.x < b.x ? -1 : a.x == b.x ? 0 : 1;
			     }
			 });
	}
	
	public void sortUp(){
		Collections.sort(_blocks,new Comparator<Block>(){
			   public int compare(Block a,Block b) {
				   return a.y < b.y ? -1 : a.y == b.y ? 0 : 1;
			     }
			 });
	}
	
	public void sortDown(){
		Collections.sort(_blocks,new Comparator<Block>(){
			   public int compare(Block a,Block b) {
				   return a.y < b.y ? 1 : a.y == b.y ? 0 : -1;
			     }
			 });
	}
	

}
