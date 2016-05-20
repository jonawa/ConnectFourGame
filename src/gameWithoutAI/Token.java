package gameWithoutAI;



import ch.aplu.jgamegrid.*;
import java.awt.Point;

public class Token extends Actor
{
  private int player, nb;
  private FourInARow1 gg;

  public Token(int player, FourInARow1 gg)
  {
    super(false, "sprites/token.png", 2);
    this.player = player;
    this.gg = gg;
    setActEnabled(false);
    show(player); // 0 = yellow , 1 = red
  }

  public void act()
  {
    Location nextLoc = new Location(getX(), getY() + 1);
    if (gameGrid.getOneActorAt(nextLoc) == null && isMoveValid())
    {
      if (nb == 6)
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
      if (gg.check4Win(getLocation()))
      {
        gg.setStatusText((player == 0 ? "Yellow" : "Red")
          + " player wins! Click on the board to play again.");
        gg.getBg().drawText("Game Over", new Point(10, 55));
        gg.finished = true;
        //gg.activeToken = null;
      }
      else
      {
        // make new Token:
        gg.activeToken = new Token((player + 1) % 2, gg);
        gg.addActor(gg.activeToken, new Location(getX(), 0),
          Location.SOUTH);
      }
      gg.setMouseEnabled(true);
    }
  }

  public int getPlayer()
  {
    return player;
  }
}
