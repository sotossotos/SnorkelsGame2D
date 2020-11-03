import java.util.Timer;
import java.util.TimerTask;
public class SpeedSnorkelMode extends ClassicMode implements GameTypes 
{
	Timer clockTimerA,clockTimerB;
	public int turnTimer;
	
	
	public SpeedSnorkelMode(int sLimit,int nStones,int timer,String playerMode,String difficulty,boolean resume)
	{
		
		super(sLimit,nStones,timer,playerMode,difficulty,resume);
	}
	public void matchExecutionHuman (int timer)
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
	public void matchExecutionAI(int timer,String difficulty)
	{
		boolean undoFlagPurple=false;
		boolean undoFlagGreen=false;
		turnTimer=timer;
		String firstPlayer="";
		String secondPlayer="";
		boolean round1=true;
		String secondPlayerC="";
		firstPlayer=chooseWhoPlay();
		if(firstPlayer.equals("human"))
		{
			secondPlayer="ai";
		}
		else
		{
			secondPlayer="human";
		}
		while(ch.getWinner().equals("") && !(numberOfMoves==0))
		{
			if(undoFlagPurple==false)
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
			isItCoordinate=A.getInputB(snorkelBoard);// it might be coordinate or quit
			//if(firstPlayer.equals("human"))
			//{
				
			//}else 
			//{
			//	isItCoordinate=A.aiInput(snorkelBoard, difficulty);
			//	System.out.println((A.getMoveX()*10)+A.getMoveY());
			//}
			clockTimerA.cancel();
			if(isItCoordinate==1)
			{
				
			snorkelBoard[A.getMoveX()-1][A.getMoveY()-1].setColour("Purple");
			gameBoard.setSnorkelTile("Purple", A.getMoveX(), A.getMoveY());
			numberOfMoves--;
			ch.starter(snorkelBoard);
			//check if lost or won
			}else if(isItCoordinate ==2 && round1==false)
			{
				undoFlagGreen=undo ();
				
			}
			else if (isItCoordinate ==2 && round1==true)
			{
				continue;
			}
			else
			{
				gameBoard.terminate();
				break;	
			}
			}
			undoFlagPurple=false;
			if(round1)
			{
				while (!secondPlayerC.equals("p")&& !secondPlayerC.equals("g"))
				{
					System.out.println("Choose the color of your snorkels: Purple(p) or Green(g)");
					if (secondPlayer.equals("human"))
					{
						secondPlayerC=sc.next();
					}
					else
					{
						secondPlayerC=B.ai.chooseColour();
						System.out.println("I choose "+B.ai.selectedColor());
					}
					
				}
				if((secondPlayerC.equals("p") && secondPlayer.equals("human")) || (secondPlayerC.equals("g") && secondPlayer.equals("ai")))
				{
					firstPlayer="human";
					secondPlayer="ai";
				}else
				{
					firstPlayer="ai";
					secondPlayer="human";
				}
				
			}
			if(ch.getWinner().equals("") && undoFlagGreen==false)
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
				
				
				if(secondPlayer.equals("human"))
				{
					isItCoordinate=B.getInputB(snorkelBoard);
					
				}else
				{
					
					isItCoordinate=B.aiInput(snorkelBoard, difficulty);
					System.out.println((B.getMoveX()*10)+B.getMoveY());
					//input from ai
				}
				
				
				clockTimerB.cancel();
				
				
				if(isItCoordinate==1)
				{
					snorkelBoard[B.getMoveX()-1][B.getMoveY()-1].setColour("Green");
					gameBoard.setSnorkelTile("Green", B.getMoveX(), B.getMoveY());
					numberOfMoves--;
					ch.starter(snorkelBoard);
				}else if (isItCoordinate ==2 && round1==false)
				{
					undoFlagPurple=undo();
				}
				else
				{
					gameBoard.terminate();
					break;
				}
			}
			undoFlagGreen=false;
			round1=false;
		}
	}
	public int getTimer()
	{
		return turnTimer;
	}
}
