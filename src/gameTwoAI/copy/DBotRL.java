package gameTwoAI.copy;

import ch.aplu.jgamegrid.*;

public class DBotRL extends IPlayer{

	  public DBotRL(int thisPlayer, GameGrid gg)
	  {
	    super(gg);
	    this.thisPlayer = thisPlayer;
	    this.enemyPlayer = (thisPlayer + 1) % 2;
	    System.out.println("RLBot: I am Player "+ this.thisPlayer);
	    System.out.println("My enemy is Player " +this.enemyPlayer);
	  }
	  
	  public int getColumn(int win)
	  {		
		  System.out.println("RLBot always chooses 1");
		  return 0;
	  }
}
