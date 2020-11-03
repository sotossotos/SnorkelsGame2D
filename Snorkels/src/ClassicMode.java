import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ClassicMode implements GameTypes
{
	public int scoreLimit;
	public int numberOfStones;
	public String gameMode;
	public Snorkel[][] snorkelBoard;
	public int numberOfMoves=48-numberOfStones;
	public GameBoard gameBoard;
	public int isItCoordinate;
	public GameChecker ch;
	public Scanner sc;
	public PlayerTurn A;
	public PlayerTurn B;
	public ClassicMode(int sLimit,int nStones,int timer)
	{
		A=new PlayerTurn();
		B=new PlayerTurn();
		ch= new GameChecker();
		sc= new Scanner(System.in);
		snorkelBoard= new Snorkel[7][7];
		initializeBoard();
		scoreLimit=sLimit;
		numberOfStones=nStones;
		gameBoard=new GameBoard();
		setStones();
		
		matchExecution(timer);
		
		if (ch.getWinner().equals("") && numberOfMoves==0)
		{
			System.out.println();
			System.out.println("The game ended in a Draw");
			gameBoard.terminate();
		}else if(!ch.getWinner().equals(""))
		{	
			System.out.println();
			System.out.println("The Winner is: "+ch.getWinner());
			System.out.println();
			gameBoard.terminate();
		}else{}
		
	}
	public void setTimer(String input)
	{
		gameBoard.timer(input);
	}
	public void matchExecution(int timer)
	{
		while(ch.getWinner().equals("") && !(numberOfMoves==0))
		{
			
			System.out.println("Player Purple Move:");
			isItCoordinate=A.getInput(snorkelBoard);// it might be coordinate or quit
			if(isItCoordinate==1)
			{
				
			snorkelBoard[A.getMoveX()-1][A.getMoveY()-1].setColour("Purple");
			gameBoard.setSnorkelTile("Purple", A.getMoveX(), A.getMoveY());
			numberOfMoves--;
			ch.starter(snorkelBoard);
			//check if lost or won
			}else
			{
				gameBoard.terminate();
				break;	
			}
			if(ch.getWinner().equals(""))
			{
				System.out.println("Player Green Move:");
				isItCoordinate=B.getInput(snorkelBoard);
			
				if(isItCoordinate==1)
				{
					snorkelBoard[B.getMoveX()-1][B.getMoveY()-1].setColour("Green");
					gameBoard.setSnorkelTile("Green", B.getMoveX(), B.getMoveY());
					numberOfMoves--;
					ch.starter(snorkelBoard);
				}else
				{
					gameBoard.terminate();
					break;
				}
			}
		}
	}
	
	public void initializeBoard()
	{
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				snorkelBoard[i][j]=new Snorkel("Blank");
			}
		}
		
		
	}
	public void setStones()
	{
		Random rand =new Random();
		int counter=1;
		
			while(counter<=numberOfStones && numberOfStones>0)
			{
				int x=rand.nextInt(7);
				int y=rand.nextInt(7);
				if (snorkelBoard[x][y].getColour()=="Blank")
				{
					snorkelBoard[x][y]=new Snorkel("Stone");
					//System.out.print(x+1);
					//System.out.print(y+1);
					System.out.println();
					gameBoard.setStoneTile(x+1, y+1);
					counter++;
				}
			}
		
	}
	
	public int getScoreLimit()
	{
		return scoreLimit;
	}
	public int getNumberOfStones()
	{
		return numberOfStones;
	}
	
	
}
