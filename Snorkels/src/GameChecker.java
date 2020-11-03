import java.util.ArrayList;

public class GameChecker 
{
	private String whoIsWinner;
	private ArrayList<String> List;
	
	public GameChecker()
	{
		List=new ArrayList<String>();
		whoIsWinner="";
	}
	public void starter( Snorkel [][] snorkelArray)
	{

		checkConnection(snorkelArray);
		checkBreathing (snorkelArray);
		//case for individual snorkel
		
		outerLoop:for (int x=0; x<=6;x++)
		{
			for(int y=0; y<=6;y++)
			{
				if (snorkelArray[x][y].getConnected()==false && snorkelArray[x][y].getBreathing()==false && !(snorkelArray[x][y].getColour().equals("Blank")) && !(snorkelArray[x][y].getColour().equals("Stone")))
				{
					whoIsWinner=oppositeColour(snorkelArray[x][y].getColour());//the winner
					
					break outerLoop;
				}
				if(snorkelArray[x][y].getConnected()==true && snorkelArray[x][y].getBreathing()==false && !(snorkelArray[x][y].getColour().equals("Blank")) && !(snorkelArray[x][y].getColour().equals("Stone") ))
				{
					checkSurround(x,y,snorkelArray);
					
				
				}
				clearVisited(snorkelArray);
				for(String winner:List)
				{
					if(winner.equals("None"))
					{
						whoIsWinner="";
						break;
					}
					whoIsWinner=winner;
				}
				List.clear();
			}
		}
		
	}
	
	private void clearVisited(Snorkel [][] snorkelArray)
	{
		for (int x=0; x<=6;x++)
		{
			for(int y=0; y<=6;y++)
			{
				snorkelArray[x][y].setVisited(false);
			}
		}
	}
	public void setWinner(String wins)
	{
		whoIsWinner=wins;
	}
	public String getWinner()
	{
		return whoIsWinner;
	}	
	private void checkSurround(int x, int y,Snorkel [][]snorkelArray)
	{
		//String winner="";
		if (snorkelArray[x][y].getBreathing()==false && snorkelArray[x][y].getConnected()==true && snorkelArray[x][y].getVisited()==false )
		{
			
			try 
			{
				if (snorkelArray[x][y].getColour().equals(snorkelArray[x-1][y].getColour()))
				{
					snorkelArray[x][y].setVisited(true);
					checkSurround( x-1,y,snorkelArray);
				}
			}catch(ArrayIndexOutOfBoundsException e)
			{}
			try 
			{
				if (snorkelArray[x][y].getColour().equals(snorkelArray[x][y-1].getColour()))
				{
					snorkelArray[x][y].setVisited(true);
					checkSurround( x,y-1,snorkelArray);
				}
			}catch(ArrayIndexOutOfBoundsException e)
			{}
			try 
			{
				if (snorkelArray[x][y].getColour().equals(snorkelArray[x+1][y].getColour()))
				{
					snorkelArray[x][y].setVisited(true);
					checkSurround( x+1,y,snorkelArray);
				}
			}catch(ArrayIndexOutOfBoundsException e)
			{}
			try 
			{
				if (snorkelArray[x][y].getColour().equals(snorkelArray[x][y+1].getColour()))
				{
					snorkelArray[x][y].setVisited(true);
					checkSurround( x,y+1,snorkelArray);
				}
			}catch(ArrayIndexOutOfBoundsException e)
			{}
		}else if (snorkelArray[x][y].getBreathing()==true && snorkelArray[x][y].getConnected()==true && snorkelArray[x][y].getVisited()==false)
		{
			snorkelArray[x][y].setVisited(true);
			List.add("None");
			return;
		}
		else
		{
			//whoIsWinner=oppositeColour(snorkelArray[x][y].getColour())+"2";
			List.add(oppositeColour(snorkelArray[x][y].getColour()));
			return;
		}
		
	}
	private String oppositeColour(String col)
	{
		if (col.equals("Green"))
		{
			return "Purple";
		}else
		{
			return "Green";
		}
	}
	private void checkBreathing ( Snorkel [][] snorkelArray)
	{
		for (int x=0; x<=6;x++)
		{
			for(int y=0; y<=6;y++)
			{
				snorkelArray[x][y].setBreathing(false);
				boolean foundBreath=false;
				try
				{
					if(snorkelArray[x-1][y].getColour().equals("Blank"))
					{
					snorkelArray[x][y].setBreathing(true);
					//foundBreath=true;
					}
					//if(!foundBreath)
					//{
					//	snorkelArray[x][y].setBreathing(false);
					//}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setBreathing(false);
				}
				try
				{
					if(snorkelArray[x][y-1].getColour().equals("Blank"))
					{
					snorkelArray[x][y].setBreathing(true);
					//foundBreath=true;
					}
					//if(!foundBreath)
					//{
					//	snorkelArray[x][y].setBreathing(false);
					//}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setBreathing(false);
				}
				try
				{
					if(snorkelArray[x+1][y].getColour().equals("Blank"))
					{
					snorkelArray[x][y].setBreathing(true);
					foundBreath=true;
					}
					//if(!foundBreath)
					//{
					//	snorkelArray[x][y].setBreathing(false);
					//}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setBreathing(false);
					
				}
				try
				{
					if(snorkelArray[x][y+1].getColour().equals("Blank"))
					{
					snorkelArray[x][y].setBreathing(true);
					//foundBreath=true;
					}
					//if(!foundBreath)
					//{
					//	snorkelArray[x][y].setBreathing(false);
					//}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setBreathing(false);
				}
				
			}	
		
		}
	}
	private void checkConnection( Snorkel [][] snorkelArray)
	{
		for (int x=0; x<=6;x++)
		{
			for(int y=0; y<=6;y++)
			{
				snorkelArray[x][y].setConnected(false);
				try
				{
					if(snorkelArray[x][y].getColour().equals(snorkelArray[x-1][y].getColour()) && !(snorkelArray[x][y].getColour().equals("Blank")) && !(snorkelArray[x][y].getColour().equals("Stone")))
					{
					snorkelArray[x][y].setConnected(true);
					}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setConnected(false);
				}
				try
				{
					if(snorkelArray[x][y].getColour().equals(snorkelArray[x][y-1].getColour()) && !(snorkelArray[x][y].getColour().equals("Blank")) && !(snorkelArray[x][y].getColour().equals("Stone")))
					{
					snorkelArray[x][y].setConnected(true);
					}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setConnected(false);
				}
				try
				{
					if(snorkelArray[x][y].getColour().equals(snorkelArray[x+1][y].getColour()) && !(snorkelArray[x][y].getColour().equals("Blank")) && !(snorkelArray[x][y].getColour().equals("Stone")))
					{
					snorkelArray[x][y].setConnected(true);
					}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setConnected(false);
				}
				try
				{
					if(snorkelArray[x][y].getColour().equals(snorkelArray[x][y+1].getColour()) && !(snorkelArray[x][y].getColour().equals("Blank")) && !(snorkelArray[x][y].getColour().equals("Stone")))
					{
					snorkelArray[x][y].setConnected(true);
					}
				}catch(ArrayIndexOutOfBoundsException e)
				{
					//snorkelArray[x][y].setConnected(false);
				}
			}
		}
	}
}
