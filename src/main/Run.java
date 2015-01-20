package main;


import javax.swing.JFrame;


public class Run {
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 1000;
	public static Game sGame;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("SLIDES");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // Prevents manual resizing of window
		sGame = new Game(GAME_WIDTH,GAME_HEIGHT);
		frame.add(sGame);
		frame.pack();
		frame.setVisible(true);
		
	}


}
