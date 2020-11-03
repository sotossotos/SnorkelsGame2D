import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerTurn 
{
	private  Move playerLastMove;
	private Move playerCurrentMove;
	private int remainingMoves;
	private String rawInput;
	private int acceptInput;// -1 is quit 1 is valid coordinates
	public Scanner sc;
	public AIplayer ai;
	public PlayerTurn()
	{
		ai=new AIplayer();
		playerCurrentMove=new Move();
		playerLastMove=new Move();
		remainingMoves=24;
	}
	public int getInput(Snorkel [][] snorks)
	{
		acceptInput=0;
		while(acceptInput==0)
		{
				sc=new Scanner(System.in);
				System.out.println("Give me xy coordinates or save or quit:");
				rawInput=sc.next();
				acceptInput=checkInput(snorks);
		}
		return acceptInput;
		
	}
	public int getInputB(Snorkel [][] snorks)
	{
		acceptInput=0;
		while(acceptInput==0)
		{
				sc=new Scanner(System.in);
				System.out.println("Give me xy coordinates or undo or save or quit:");
				rawInput=sc.next();
				acceptInput=checkInputB(snorks);
		}
		return acceptInput;
	
	}
	public int getLastMoveX()
	{
		return playerLastMove.getX();
	}
	public int getLastMoveY()
	{
		return playerLastMove.getY();
	}
	public int aiInput(Snorkel [][] snorks,String difficulty)
	{
		playerLastMove.setX(playerCurrentMove.getX());
		playerLastMove.setX(playerCurrentMove.getY());
		if(difficulty.equals("easy"))
		{
			ai.thinkEasyMove(snorks);
		}
		if (difficulty.equals("hard"))
		{
			ai.thinkHardMove(snorks);
		}
		playerCurrentMove.setX(ai.getX());
		playerCurrentMove.setY(ai.getY());
		return 1;
	}
	public int getMoveX()
	{
		return playerCurrentMove.getX();
	}
	public int getMoveY()
	{
		return playerCurrentMove.getY();
	}
	public int checkValidCoordinates(int coord,Snorkel [][] snorks)
	{
		if(coord / 10 >=1 && coord / 10 <=7 && coord % 10 >=1 && coord % 10 <=7)
		{
			if(snorks[(coord / 10)-1][(coord % 10)-1].getColour().equals("Blank"))
			{
				playerLastMove.setX(playerCurrentMove.getX());
				playerLastMove.setX(playerCurrentMove.getY());
				playerCurrentMove.setX(coord / 10 );
				playerCurrentMove.setY(coord % 10);
				remainingMoves--;
				return 1;
			}
		}
		System.out.println("Invalid coordinates!");
		return 0;
	}
	public Move getCurrentMove()
	{
		return playerCurrentMove;
	}
	public int checkInput(Snorkel [][] snorks)
	{
		if(rawInput.equals("quit"))
		{
			System.out.println("Quitting to main menu");
			return -1;
		}
		else if(rawInput.equals("save"))
		{
			//System.out.println("Saved!");
			return 3;
		} 
		else
		{
			try
			{
				Integer.parseInt(rawInput);
				return checkValidCoordinates(Integer.parseInt(rawInput),snorks);

			}catch(NumberFormatException e)
			{
				System.out.println("Invalid coordinates!");
				return 0;
			}
		}
		
	
		
	}
	public int checkInputB(Snorkel [][] snorks)
	{
		if(rawInput.equals("quit"))
		{
			System.out.println("Quitting to main menu");
			return -1;
		}
		else if (rawInput.equals("undo"))
		{
			System.out.println("Your move has been undone");
			return 2;
		}
		else if (rawInput.equals("save"))
		{
			System.out.println("Saved!");
			return 3;
		}
		else  
		{
			try
			{
				Integer.parseInt(rawInput);
				return checkValidCoordinates(Integer.parseInt(rawInput),snorks);

			}catch(NumberFormatException e)
			{
				System.out.println("Invalid coordinates!");
				return 0;
			}
		}	
	}
	
	public int getRemainingMoves()
	{
		return remainingMoves;
	}
}
