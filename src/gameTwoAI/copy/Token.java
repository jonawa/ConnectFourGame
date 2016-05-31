package gameTwoAI.copy;



import java.awt.Point;
import ch.aplu.jgamegrid.*;
import ch.aplu.util.Monitor;

public class Token extends Actor
{
  private int player, nb;
  private FourInARow2 gg;

  public Token(int player, FourInARow2 gg)
  {
    super(false, "sprites/token.png", 2);
    this.player = player;
    this.gg = gg;
    setActEnabled(true); // setze auf true um direkt mit den KIs zu starten.
    show(player); // 0 = yellow , 1 = red
  }

  public void act()
  {
	  
	Location nextLoc = new Location(getX(), getY() + 1);
    if (gameGrid.getOneActorAt(nextLoc) == null && isMoveValid())
    {
      if (nb == FourInARow2.ROWS-1)
      {
        nb = 0;
        setLocationOffset(new java.awt.Point(0, 0));
        move();
      }
      else
        setLocationOffset(new java.awt.Point(0, 10 * nb));
      nb++;
    }
    else
    { //token has arrived
      setActEnabled(false); 
      IPlayer.board[getX()][Math.abs(getY() - (FourInARow2.ROWS-1))] = this; //put into table for computers move
      if (gg.check4Win(getLocation()))
      {
        gg.setStatusText((player == 0 ? "RL-Bot won!" : "RL-Bot lost!")
          + " Click on the board to play again.");
        gg.getBg().drawText("Game Over", new Point(10, 55));
        System.out.format("-----------------------------\n Spieler %d gewinnt \n-----------------------\n",player);
        gg.finished = true;
        gg.refresh();
        gg.reset();
        Monitor.putSleep(2000); // wait for 2 seconds
      
      }
      else
      {
        // make new Token:
        gg.activeToken = new Token((player + 1) % 2, gg);
        gg.addActor(gg.activeToken, new Location(getX(), 0),
          Location.SOUTH);
      }
      if (!gg.finished)
    	System.out.println("Computer move for Player" + this.player);
        gg.computerMove();
      
    	  
    }
  }

  public int getPlayer()
  {
    return player;
  }
}
