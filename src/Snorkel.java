
public class Snorkel 
{
	public String colour;
	boolean isBreathing;
	boolean isConnected;
	boolean visited;
	
	public Snorkel(String col)
	{
		colour=col;
		isBreathing=false;
		isConnected=false;
		visited=false;
	}
	public void setColour(String col)
	{
		colour=col;
	}
	public boolean getVisited ()
	{
		return visited;
	}
	public void setVisited (boolean visit)
	{
		visited=visit;
	}
	public void setBreathing(boolean breath)
	{
		isBreathing=breath;
	}
	
	public boolean getBreathing()
	{	
		return isBreathing;
	}
	public void setConnected(boolean connect)
	{	
		isConnected=connect;
	}
	public boolean getConnected()
	{	
		return isConnected;
	}
	public String getColour()
	{	
		return colour;
	}
}
