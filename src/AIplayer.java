import java.util.Random;
public class AIplayer 
{
	Random rand;
	int xCoordinate=1;
	int yCoordinate=1;
	String colour="";
	public AIplayer()
	{
		rand= new Random();
	}
	public String chooseColour()
	{
		int pickColour;
		pickColour=rand.nextInt(2);
		if(pickColour==1)
		{
			
			return colour="p";
		}
		return colour="g";
	}
	public void setColour(String col)
	{
		colour=col;
	}
	public String selectedColor()
	{
		return colour;
	}
	public void thinkEasyMove(Snorkel[][] snorkelBoard)
	{
		do
		{
			xCoordinate=rand.nextInt(7)+1;
			yCoordinate=rand.nextInt(7)+1;
		}
		while(!(snorkelBoard[xCoordinate-1][yCoordinate-1].getColour().equals("Blank")));
	}
	public void thinkHardMove(Snorkel[][] snorkelBoard)
	{
	}
	public int getX()
	{
		return xCoordinate;
	}
	public int getY()
	{
		return yCoordinate;
	}
	public int makeHardMove()
	{
		return 1;
	}
}
