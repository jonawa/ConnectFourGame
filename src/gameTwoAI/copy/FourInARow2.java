package gameTwoAI.copy;



import ch.aplu.jgamegrid.*;
import db.TestDB;
import util.Helper;

import java.awt.*;
import java.util.Arrays;


public class FourInARow2 extends GameGrid implements GGMouseListener
{
  private int currentPlayer = 0;
  public boolean finished = false;
  Token activeToken;
  private IPlayer ComputerPlayer;
  private IPlayer ComputerPlayerRL;
//<<<<<<< HEAD
  
  /**
   * Füge Variable für die Anzahl Steine zum Gewinnen hinzu:
   */
  public static int WIN= 3;
  /**
   * Anzahl Reihen wird immer um 1 erhöht sonst passt es nicht im Gamegrid
   */
  public static int ROWS = 6+1 ; 
  public static int COLUMNS = 7 ;

  public FourInARow2()
  {
    super(COLUMNS, ROWS, 70, null, null, false);
    
    
    
    //addMouseListener(this, GGMouse.lPress | GGMouse.move);
    this.getBg().setBgColor(Color.white);
    activeToken = new Token(currentPlayer, this);
    // Füge hinzu:
    Token activeTokenBot = new Token(currentPlayer+1, this);
    
    addActor(activeToken, new Location(0, 0), Location.SOUTH);
    addActor(new BG(), new Location(3, -1)); //outside of grid, so it doesn't disturb game
    getBg().setFont(new Font("SansSerif", Font.BOLD, 48));
    getBg().setPaintColor(Color.red);
    show();
    
    setSimulationPeriod(30);
    doRun();
    addStatusBar(30);
    setTitle("Four In A Row (against Computer). Developed by Stefan Moser.");
    
    
    ComputerPlayer = new DBot(1, this); 
    ComputerPlayerRL = new DBotRL(0, this); 
    for (Token[] column : DBot.board) //fill board with "empty" stones
      Arrays.fill(column, new Token(-1, this));
    System.out.println();
    
  }

  public void reset()
  {
	System.out.println("Reset");
    getBg().clear();
    removeActors(Token.class); //remove all tokens
    for (Token[] column : DBot.board) //fill board with "empty" stones
      Arrays.fill(column, new Token(-1, this));
    currentPlayer = 0; //RL player always starts (bc i'm lazy)
    setStatusText("Game reset! " + (currentPlayer == 0 ? "Yellow" : "Red") + " player begins.");
    activeToken = new Token(currentPlayer, this);
    addActor(activeToken, new Location(0, 0), Location.SOUTH);
    finished = false;
  }

  public void computerMove()
  {
	System.out.println("ComputerMove: CurrentPlayer =" +currentPlayer);
    setMouseEnabled(false);
    int col;
    if (currentPlayer==1){
    	System.out.println("Default Bot turn");
    	col = ComputerPlayer.getColumn(WIN);
    	
    	//Print Board
    	int[][] board = Helper.convertTokenBoardToInt(DBot.board);
    	System.out.println(Helper.convertIntBoardToString(board));
    }
    else{
    	System.out.println("RL turn");
    	col = ComputerPlayerRL.getColumn(WIN) ;
     	
    	//Print Board
    	int[][] board = Helper.convertTokenBoardToInt(DBot.board);
    	System.out.println(Helper.convertIntBoardToString(board));
    	

    	
    	//In die Datenbank einfügen:
    	TestDB db = TestDB.getDB();
    	db.put(board, col, 1000);
    	System.out.println(db.toString());
    }
    System.out.println("Set Token to " + (col+1));
    activeToken.setX(col);
    activeToken.setActEnabled(true);
    currentPlayer = (currentPlayer + 1) % 2; //change Player

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
    return (checkVertically(col, row, WIN) || checkHorizontally(col, row, WIN)
      || checkDiagonally1(col, row, WIN)
      || checkDiagonally2(col, row, WIN));

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
  
  public static void main(String[] args)
  {
    new FourInARow2();
  }
}

