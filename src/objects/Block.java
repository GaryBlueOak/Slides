package objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Block extends Rectangle{
	public static int BLOCK_SIZE = 60;
	public static int SLIDE_SPEED = 15;// must be a multiple of BLOCK_SIZE

	private int _colorIndex;
	private boolean _collision;
	protected int _xDirection;
	protected int _yDirection;
	//protected int _xPos;
	//protected int _yPos;
	private Color _color;
	public boolean _sliding;
	public String print = "";
	public boolean _toDelete = false;
	
	
	public Block(int colorIndex, int xPos, int yPos){
		_colorIndex = colorIndex;
		this.x = xPos;
		this.y = yPos;
		this.width = Block.BLOCK_SIZE;
		this.height = Block.BLOCK_SIZE;
		_xDirection = 0;
		_yDirection = 0;
		_sliding = false;
		generateColor();
	}

	public boolean getCollision(Rectangle rect,Board board){
		int counter = 0;
		for(Block b: board.getBlocks()){
			if((rect.intersects(b) && !this.equals(b))){
				if(this.getColorIndex() == b.getColorIndex()){
					_toDelete = true;
					return false;
				}
				
				return true;
			}
		}
		return false;
	}
	
	public boolean getRightCollision(Board board){
		return (x+width+Block.SLIDE_SPEED > board.getRight());
	}
	
	public boolean getLeftCollision(Board board){
		return x-Block.SLIDE_SPEED < board.getLeft();
	}
	
	public boolean getDownCollision(Board board){
		return y + width + Block.SLIDE_SPEED >board.getDown();
	}
	
	public boolean getUpCollision(Board board){
		return y-Block.SLIDE_SPEED < board.getUp();
	}
	
	public boolean slideRight(Board board){
		Rectangle collision = new Rectangle(this.x + Block.SLIDE_SPEED, this.y, Block.BLOCK_SIZE, Block.BLOCK_SIZE);
		if(_sliding && !getCollision(collision,board) && !getRightCollision(board)){
			x = x + Block.SLIDE_SPEED;
			return true;
		}
		_sliding = false;
//		checkRemove(board);
		return false;
	}
	
	public boolean slideLeft(Board board){
		Rectangle collision = new Rectangle(this.x - Block.SLIDE_SPEED, this.y, Block.BLOCK_SIZE, Block.BLOCK_SIZE);
		if(_sliding && !getCollision(collision,board) && !getLeftCollision(board)){
			x = x - Block.SLIDE_SPEED;
			return true;
		}
		_sliding = false;
//		checkRemove(board);
		return false;
	}
	
	public boolean slideUp(Board board){
		Rectangle collision = new Rectangle(this.x, this.y- Block.SLIDE_SPEED, Block.BLOCK_SIZE, Block.BLOCK_SIZE);
		if(_sliding && !getCollision(collision,board) && !getUpCollision(board)){
			y = y - Block.SLIDE_SPEED;
			return true;
		}
		_sliding = false;
//		checkRemove(board);
		return false;
	}
	
	public boolean slideDown(Board board){
		Rectangle collision = new Rectangle(this.x, this.y+ Block.SLIDE_SPEED, Block.BLOCK_SIZE, Block.BLOCK_SIZE);
		if(_sliding && !getCollision(collision,board) && !getDownCollision(board)){
			y = y + Block.SLIDE_SPEED;
			return true;
		}
		_sliding = false;
//		checkRemove(board);
		return false;
	}
	
	public void checkRemove(Board board){
		if(_toDelete){
			board.getBlocks().remove(this);
		}
	}
	
	public Color getColor(){
		return _color;
		
	}
	
	private void generateColor(){
		switch(_colorIndex){
			case 1:
				_color = new Color(155, 89, 182);
				break;
			case 2:
				_color = new Color(52, 152, 219);
				break;
			case 3:
				_color = new Color(26, 188, 156);
				break;
			case 4:
				_color = new Color(155, 89, 182);
				break;
			case 5:
				_color = new Color(52, 73, 94);
		}
	}
	
	public int getColorIndex(){
		return _colorIndex;
	}
	

	
	
}
