/**
 * 
 */
package qLearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import db.TestDB;
import gameTwoAI.copy.FourInARow2;

/**
 * Class QLearning
 * 
 * @author Lena
 *
 *
 */
public class QLearning {
	
	//Set of states
    //TODO Anlegen
	
	/**
	 * Count of states. ?
	 */
	private int statesCount;
	
	// Set of actions
	//TODO Anlegen
	
	/**
	 * Count of actions. 
	 */
	private int actionsCount = FourInARow2.WIN;
	
	/**
	 * Count of columns. 
	 */
	private int col = FourInARow2.COLUMNS;
	
	/**
	 * Count of rows. 
	 */
	private int row = FourInARow2.ROWS;
	
    /**
     * Discount factor
     * to weight the importance of future rewards.
     * Could be between 0 or 1:
     * 0 - considers only current reward 
     * 1 - considers long-term rewards 
     */
    private double gamma = 0.9;
	
	/**
	 * Learning rate or step size.
	 */
    private double alpha = 0.1;
    
    /**
     * Matrix Q.
     * Q[S,A], where S is the set of states and A is the set of actions, 
     * represents the current estimate of rewards. 
     */
    private double[][] Q = new double[getStatesCount()][getActionsCount()];
    
    /**
     * Matrix R.
     * R[S,A], where S is the set of states and A is the set of actions, 
     * represents the reward for a state and an action.
     */
    private int[][] R = new int[getStatesCount()][getActionsCount()];
    
    private TestDB database = null;
   
    /**
     * Speichert die ausgeführten Aktionen und Bewertungen.
     * 
     */
    private List<Move> gameplay = new ArrayList<Move>();
    
    private int getAction(int[][] state) {
		
    	if (database == null){
    		database=TestDB.getDB();
    	}
    	
    	int action = database.maxValueActionForState(state);
		gameplay.add(new Move(state, action)); 
		
		if (checkWin()){
			for(Move m: gameplay){
				database.put(m.getState(), m.getColumn(), 1);
			}
			database.saveDB();
		}
		
		return action;
		
		/* TODO Rest vom Algo impl.
        repeat
			select and carry out an action a
			observe reward r and state s'
			Q[s,a] <- Q[s,a] + alpha(r + gamma * maxQ[s',a'] - Q[s,a])
			s <- s'
		until termination
        */
	}
	
	private boolean checkWin(){
		return false;
	}
	
	
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long BEGIN = System.currentTimeMillis();
		 
        QLearning obj = new QLearning();
 
        //obj.run();
 
        long END = System.currentTimeMillis();
        System.out.println("Time: " + (END - BEGIN) / 1000.0 + " sec.");
	}

	
	public double getQ(int state, int action) {
        return Q[state][action];
    }
 
    public void setQ(int state, int action, double value) {
        Q[state][action] = value;
    }
    
    private int getStatesCount() {
		return statesCount;
	}
    
    private int getActionsCount() {
		return actionsCount;
	}
    
    
   
}
