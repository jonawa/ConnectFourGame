package gameTwoAI.copy;

import ch.aplu.jgamegrid.*;

public class DBotRL extends IPlayer{

	  public DBotRL(int thisPlayer, GameGrid gg)
	  {
	    super(gg);
	    this.thisPlayer = thisPlayer;
	    this.enemyPlayer = (thisPlayer + 1) % 2;
	  }
	  
	  public int getColumn()
	  {
		  return 0;
	  }
}
