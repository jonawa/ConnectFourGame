package qLearning;


/**
 * 
 * @author Lena
 *
 *	Klasse zum Speichern er einzelnen Z�ge im Spielverlauf.
 */
public class Move {

	    	
    	private int[][] state;
    	private int column;
    	
    	public Move(int[][] s, int c){
    		state = s;
    		column = c;
    	}
    	
    	public int[][] getState(){
    		return state;
    	}
    	
    	public int getColumn(){
    		return column;
    	}
	
}
