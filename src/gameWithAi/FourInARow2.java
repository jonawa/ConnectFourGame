package gameWithAi;



import ch.aplu.jgamegrid.*;
import gameTwoAI.copy.DBot;
import util.Helper;

import java.awt.*;
import java.util.Arrays;

public class FourInARow2 extends GameGrid implements GGMouseListener
{
  private int currentPlayer = 0;
  public boolean finished = false;
  Token activeToken;
  private IPlayer ComputerPlayer;
  private String moveInfo = "Move mouse to a column and click to set the token.";
  
  public static int ROWS = 6 +1;
  public static int COLUMNS = 7 ;
  
  //Anzahl der Spielsteine die f�rs Gewinnen gebraucht werden.
  public static int WINCOUNT = 4;

  public FourInARow2()
  {
    super(COLUMNS, ROWS, 70, null, null, false);
    addMouseListener(this, GGMouse.lPress | GGMouse.move);
    this.getBg().setBgColor(Color.white);
    activeToken = new Token(currentPlayer, this);
    addActor(activeToken, new Location(0, 0), Location.SOUTH);
    addActor(new BG(), new Location(3, -1)); //outside of grid, so it doesn't disturb game
    getBg().setFont(new Font("SansSerif", Font.BOLD, 48));
    getBg().setPaintColor(Color.red);
    show();
    //Wie lange die Steine brauchen zum runterfallen:
    setSimulationPeriod(0);
    doRun();
    addStatusBar(30);
    setStatusText(moveInfo);
    setTitle("Four In A Row (against Computer). Developed by Stefan Moser.");
    
    ComputerPlayer = new gameWithAi.DBot(1, this); //menu for choosing?
    for (Token[] column : gameWithAi.DBot.board) //fill board with "empty" stones
      Arrays.fill(column, new Token(-1, this));
     
    
  }

  public void reset()
  {
    getBg().clear();
    removeActors(Token.class); //remove all tokens
    for (Token[] column : gameWithAi.DBot.board) //fill board with "empty" stones
      Arrays.fill(column, new Token(-1, this));
    currentPlayer = 0; //Human player always starts (bc i'm lazy)
    setStatusText("Game reset! " + (currentPlayer == 0 ? "Yellow" : "Red") + " player begins.");
    activeToken = new Token(currentPlayer, this);
    addActor(activeToken, new Location(0, 0), Location.SOUTH);
    finished = false;
  }

  public void computerMove()
  {
    setMouseEnabled(false);
    int col = ComputerPlayer.getColumn();
    activeToken.setX(col);
    activeToken.setActEnabled(true);
    currentPlayer = (currentPlayer + 1) % 2; //change Player
    setStatusText(moveInfo);
	int[][] board = Helper.convertTokenBoardToInt(DBot.board);
	System.out.println(Helper.convertIntBoardToString(board));
  }

  @Override
  public boolean mouseEvent(GGMouse mouse)
  {

    Location mouseLoc = toLocation(mouse.getX(), mouse.getY());
    if (mouse.getEvent() == GGMouse.move)
    { //move active Token with mouse
      if (!finished && activeToken.getX() != mouseLoc.x)
         activeToken.setX(mouseLoc.x);
      return true;
    }

    if (finished)
    {
      reset();
      return true;
    }

    if (getOneActorAt(new Location(mouseLoc.x, 1)) == null)
    { //drop Token if column isn't full
      activeToken.setActEnabled(true);
      setMouseEnabled(false);
      currentPlayer = (currentPlayer + 1) % 2;
    }
    else
    {
      setStatusText("This column is full.");
    }

    return true;
  }

  public int getPlayerOfTokenAt(int x, int y)
  {
    Location loc = new Location(x, y);
    if (getOneActorAt(loc) == null)
      return -1;
    else
      return ((Token)getOneActorAt(loc)).getPlayer();
  }

  // @param the location of newly inserted token
  // @return true, if four are connected through that token

  public boolean check4Win(Location loc)
  {
    int col = loc.x;
    int row = loc.y;
    return (checkVertically(col, row, WINCOUNT) || checkHorizontally(col, row, WINCOUNT )
      || checkDiagonally1(col, row, WINCOUNT)
      || checkDiagonally2(col, row, WINCOUNT));

  }

  private boolean checkDiagonally2(int col, int row, int nrOfTokens)
  {
    for (int j = 0; j < nrOfTokens; j++)
    {
      int adjacentSameTokens = 0;
      for (int i = 0; i < nrOfTokens; i++)
      {
        if ((col - i + j) >= 0 && (col - i + j) < nbHorzCells
          && (row + i - j) >= 1 && (row + i - j) < nbVertCells
          && getPlayerOfTokenAt(col - i + j, row + i - j) == getPlayerOfTokenAt(col, row))
        {
          adjacentSameTokens++;
        }
      }
      if (adjacentSameTokens >= nrOfTokens)
        return true;
    }
    return false;
  }

  private boolean checkDiagonally1(int col, int row, int nrOfTokens)
  {
    for (int j = 0; j < nrOfTokens; j++)
    {
      int adjacentSameTokens = 0;
      for (int i = 0; i < nrOfTokens; i++)
      {
        if ((col + i - j) >= 0 && (col + i - j) < nbHorzCells
          && (row + i - j) >= 1 && (row + i - j) < nbVertCells
          && getPlayerOfTokenAt(col + i - j, row + i - j) == getPlayerOfTokenAt(col, row))
        {
          adjacentSameTokens++;
        }
      }
      if (adjacentSameTokens >= nrOfTokens)
        return true;
    }
    return false;
  }

  private boolean checkHorizontally(int col, int row, int nrOfTokens)
  {
    int adjacentSameTokens = 1;
    int i = 1;
    while (col - i >= 0 && getPlayerOfTokenAt(col - i, row) == getPlayerOfTokenAt(col, row))
    {
      adjacentSameTokens++;
      i++;
    }
    i = 1;
    while (col + i < nbHorzCells && getPlayerOfTokenAt(col + i, row) == getPlayerOfTokenAt(col, row))
    {
      adjacentSameTokens++;
      i++;
    }
    return (adjacentSameTokens >= nrOfTokens);
  }

  private boolean checkVertically(int col, int row, int nrOfTokens)
  {
    int adjacentSameTokens = 1;
    int i = 1;
    while (row + i < nbVertCells && getPlayerOfTokenAt(col, row + i) == getPlayerOfTokenAt(col, row))
    {
      adjacentSameTokens++;
      i++;
    }
    return (adjacentSameTokens >= nrOfTokens);
  }

  public static void main(String[] args)
  {
    new FourInARow2();
  }
}

