package util;

import gameTwoAI.copy.DBot;
import gameTwoAI.copy.Token;


/**
 * Helper Klasse in dem wir alles auslagern können, was wir nicht in die Main packen wollen, aber keine eigene Klasse rechtfertigt.
 * Dient der Übersicht
 * @author jcawa
 *
 */
public class Helper {


    /**
     * Konvertiert vom Spiel erzeugetes Token[][] Board in ein 2D in Array.
     * Dabei wird das Board einmal gespiegelt auf Grund der Anordnung im ursprünglichen Array.
     * Diese Methode ist Basis für die Speicherung in die Datenbank bzw. die Konvertierung in Binärcode
     * und die anschließende Speicherung in der Datenbank
     * @param tokens
     * @return
     */
    public static int[][] convertTokenBoardToInt(Token[][] tokens){
    	//create new array
    	
    	int[][] intArr =  new int[tokens[0].length][tokens.length];
    	int j = 0;
    	
    	for(Token[] rows : tokens){
    		int i = 0;
    		for(Token token : rows){
    			intArr[tokens[0].length -i -1][j] = token.getPlayer();
    			i++;
    		}
    		j++;
    	}
    	
    	return intArr;
    }
    /**
     * Konvertiert das Token[][] Board in einen String, der auf der z.B. auf der Konsole ausgegeben werden kann
     * Achtung: Das Spielfeld wird nicht in der richtigen Reihenfolge ausgegeben.
     * Erst die Methode convertTokenBoardToInt benutzen!
     * @param tokens
     * @return
     */
    public static String convertTokenBoardToString(Token[][] tokens){
		StringBuilder sb = new StringBuilder();
		for(Token[] rows : tokens){
			for(Token token : rows){
				sb.append("|");
				sb.append(token.getPlayer());
				
			}
			sb.append("|");
			sb.append("\n");
		}
		
		return sb.toString();
    }
    /**
     * Konvertiert ein beliebiges int 2D Array in einen String zur Ausgabe 
     * @param board
     * @return
     */
    public static String convertIntBoardToString(int[][] board){
		StringBuilder sb = new StringBuilder();
		for(int[] rows : board){
			for(int token : rows){
				sb.append("|");
				sb.append(token);
				
			}
			sb.append("|");
			sb.append("\n");
		}
		
		return sb.toString();
    }

    
    
    public static String convertIntBoardTo1DString(int[][] board){
		StringBuilder sb = new StringBuilder();
		for(int[] rows : board){
			for(int token : rows){		
				sb.append(token);
			}

		}
		
		return sb.toString();
    }

}
