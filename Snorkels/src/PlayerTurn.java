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
	public PlayerTurn()
	{
		
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
				System.out.println("Give me xy coordinates or quit:");
				rawInput=sc.next();
				acceptInput=checkInput(snorks);
		}
		return acceptInput;
		
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
			if(snorks[(coord / 10)-1][(coord % 10)-1].getColour()=="Blank")
			{
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
		}else  
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
