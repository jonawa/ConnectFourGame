package letzterVersuch;

// DBot.java



import java.util.Random;

import ch.aplu.jgamegrid.GameGrid;

public class DBot2 extends IPlayer {
	private Random rand;

	public DBot2(int thisPlayer, GameGrid gg) {
		super(gg);
		this.thisPlayer = thisPlayer;
		this.enemyPlayer = (thisPlayer + 1) % 2;

		rand = new Random(1L); // Remove 
	}

	public int getColumn() {
		
		int col = rand.nextInt(board.length);
		
		int nextRow = insertToken(col) + 1;
		while(nextRow>board[col].length){
			col = rand.nextInt(board.length);
			nextRow = insertToken(col) + 1;
		}
		

		
		
		return col;
	}
}
