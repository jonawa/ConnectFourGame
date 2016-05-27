package util;

import gameTwoAI.copy.DBot;
import gameTwoAI.copy.Token;

public class Util {
	
	/**
	 * Transposes 2D int Array
	 * @param array
	 * @return
	 */
    public static int[][] transposeArray (int[][] array) {
    	
	  if (array == null || array.length == 0)//empty or unset array, nothing do to here
		  return array;
	
	  int width = array.length;
	  int height = array[0].length;
	
	  int[][] array_new = new int[height][width];
	
	  for (int i = 0; i < width; i++) {
		  for (int j = 0; j < height; j++) {
			  array_new[j][i] = array[i][j];
	    }
	  }
	  return array_new;
}    
   

    
    public static int[][] convertTokenBoardToInt(Token[][] tokens){
    	//create new array

    	int[][] intArr =  new int[tokens[].length][tokens.length];
    	
    	for(Token[] rows : DBot.board){
    		for(Token token : rows){
    			
    		}
    	}
    }
}
