import java.util.Timer;
import java.util.TimerTask;
public class SpeedSnorkelMode extends ClassicMode implements GameTypes 
{
	Timer clockTimerA,clockTimerB;
	public int turnTimer;
	
	
	public SpeedSnorkelMode(int sLimit,int nStones,int timer)
	{
		
		super(sLimit,nStones,timer);
	}
	public void matchExecution (int timer)
	{
		
		
		turnTimer=timer;
		
		while(ch.getWinner().equals("") && !(numberOfMoves==0))
		{
			
			System.out.println("Player Purple Move:");
			clockTimerA = new Timer();
			clockTimerA.schedule(new TimerTask(){
				public void run()
				{
					
					ch.setWinner("Green");
					System.out.println("Time's up, write quit to be transfered to the main menu");
					
					
					
				}
			} , turnTimer*1000);
				
			isItCoordinate=A.getInput(snorkelBoard);
			clockTimerA.cancel();
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
				clockTimerB = new Timer();
				clockTimerB.schedule(new TimerTask(){
					public void run()
					{
						
						ch.setWinner("Purple");
						System.out.println("Time's up, write quit to be transfered to the main menu");
						
						
						
					}
				} , turnTimer*1000);
				isItCoordinate=B.getInput(snorkelBoard);
			    clockTimerB.cancel();
				
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
	public int getTimer()
	{
		return turnTimer;
	}
}
