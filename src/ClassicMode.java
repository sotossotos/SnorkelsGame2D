import java.util.Random;
import java.util.Scanner;
import java.io.*;


public class ClassicMode implements GameTypes
{
	public int scoreLimit;
	public int numberOfStones;
	public String gameMode;
	public Snorkel[][] snorkelBoard;
	public int numberOfMoves=48;
	public GameBoard gameBoard;
	public int isItCoordinate;
	public GameChecker ch;
	public Scanner sc;
	public String playerMode;
	public PlayerTurn A;
	public PlayerTurn B;
	public String difficulty;
	public String currentPlayer="Purple";//this is for the 
	public ClassicMode(int sLimit,int nStones,int timer,String playerMode,String difficulty,boolean resume)
	{
		A=new PlayerTurn();
		B=new PlayerTurn();
		ch= new GameChecker();
		sc= new Scanner(System.in);
		this.playerMode=playerMode;
		this.difficulty=difficulty;
		snorkelBoard= new Snorkel[7][7];
		initializeBoard();
		//numberOfMoves;
		scoreLimit=sLimit;
		numberOfStones=nStones;
		gameBoard=new GameBoard();
		
		if (resume)
		{
			resumeGame();//code for the resume part change array2D change graphics
		}else
		{
			setStones();
		}
		if(playerMode.equals("2"))
		{
			matchExecutionHuman(timer);	
		}
		if(playerMode.equals("1"))
		{
			matchExecutionAI(timer,difficulty,resume);
		}
		
		
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
	public void matchExecutionHuman(int timer)
	{
		
		while(ch.getWinner().equals("") && !(numberOfMoves==0))
		{
			if(currentPlayer.equals("Purple"))
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
			}else if(isItCoordinate==3 )
			{
				save();
				gameBoard.terminate();
				break;
			}
			else
			{
				gameBoard.terminate();
				break;	
			}
			currentPlayer="Green";
			}
			if(currentPlayer.equals("Green"))
			{
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
					
				}else if(isItCoordinate==3)
				{
					save();
					gameBoard.terminate();
					break;
				}
				else
				{
					gameBoard.terminate();
					break;
				}
			}
			currentPlayer="Purple";
			}
		}
	}
	public String chooseWhoPlay()
	{
		String humanAns="";
		while(!(humanAns.equals("1"))&&!(humanAns.equals("2")))
		{
			System.out.println("Do you want to play first (1) or second (2):");
			humanAns=sc.next();
		}
		if(humanAns.equals("1"))
		{
			return "human";
		}
		return "ai";
		
		
	}
	public void matchExecutionAI(int timer,String difficulty,boolean resume)
	{
		boolean undoFlagPurple=false;
		boolean undoFlagGreen=false;
		String firstPlayer="";
		String secondPlayer="";
		boolean round1=true;
		String secondPlayerC="";
		if(!resume)
		{
			firstPlayer=chooseWhoPlay();
			if(firstPlayer.equals("human"))
			{
				secondPlayer="ai";
			}
			else
			{
				secondPlayer="human";
			}
		}else
		{
			round1=false;
		}
		while(ch.getWinner().equals("") && !(numberOfMoves==0))
		{
			
			if(undoFlagPurple==false && currentPlayer.equals("Purple"))
			{
			
			System.out.println("Player Purple Move:");
			if(firstPlayer.equals("human"))
			{
				isItCoordinate=A.getInputB(snorkelBoard);// it might be coordinate or quit
			}else 
			{
				isItCoordinate=A.aiInput(snorkelBoard, difficulty);
				System.out.println((A.getMoveX()*10)+A.getMoveY());
				
			}
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
			}else if (isItCoordinate==3 && round1==false)
			{
				System.out.println("Saved!");
				save();
				gameBoard.terminate();
				break;
			}//else if(isItCoordinate==3 && round1==true)
			//{
			
		//		System.out.println("You cannot save in the first round");
		//	}
			else
			{
				gameBoard.terminate();
				break;	
			}
			
			}
			currentPlayer="Green";
			undoFlagPurple=false;
			if(round1 )
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
					B.ai.setColour("Green");//identifies the colour of the AI
				}else
				{
					firstPlayer="ai";
					secondPlayer="human";
					A.ai.setColour("Purple");//identifies the colour of the AI
				}
				
			}
			round1=false;
			if(ch.getWinner().equals("") && undoFlagGreen==false && currentPlayer.equals("Green"))
			{
				
				System.out.println("Player Green Move:");
				if(secondPlayer.equals("human"))
				{
					isItCoordinate=B.getInputB(snorkelBoard);
					
				}else
				{
					
					isItCoordinate=B.aiInput(snorkelBoard, difficulty);
					System.out.println((B.getMoveX()*10)+B.getMoveY());
					//input from ai
				}
			
				if(isItCoordinate==1)
				{
					snorkelBoard[B.getMoveX()-1][B.getMoveY()-1].setColour("Green");
					gameBoard.setSnorkelTile("Green", B.getMoveX(), B.getMoveY());
					numberOfMoves--;
					ch.starter(snorkelBoard);
				}else if (isItCoordinate ==2 && round1==false)
				{
					undoFlagPurple=undo();
				}else if (isItCoordinate==3 && round1==false)
				{
					save();
					gameBoard.terminate();
					break;
				}
				else
				{
					gameBoard.terminate();
					break;
				}
			}
			currentPlayer="Purple";
			undoFlagGreen=false;
			
		}
	}
	public boolean undo()
	{
		//not sure yet about checking see round1
		gameBoard.setTileBlank(A.getMoveX(), A.getMoveY());
		gameBoard.setTileBlank(B.getMoveX(), B.getMoveY());
		snorkelBoard [A.getMoveX()-1] [A.getMoveY()-1].setColour("Blank");
		snorkelBoard [B.getMoveX()-1] [B.getMoveY()-1].setColour("Blank");
		numberOfMoves +=2;
		return true;
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
	public void resumeGame()
	{
		try
			{
				FileReader fr = new FileReader("saveClassic1.txt");
				BufferedReader reader= new BufferedReader(fr);
				String line =reader.readLine();
				line=reader.readLine();
				line=reader.readLine();
				line=reader.readLine();
				line=reader.readLine();
				line=reader.readLine();
				Scanner scan =  new Scanner(line);
				currentPlayer=scan.next();
				line=reader.readLine();
				scan=new Scanner(line);
				numberOfMoves=scan.nextInt();
				for (int x=0;x<7;x++)
				{
					line=reader.readLine();
					scan=new Scanner(line);
					for(int y=0;y<7;y++)
					{
						snorkelBoard[x][y].setColour(scan.next());
						if(snorkelBoard[x][y].getColour().equals("Purple"))
						{
							gameBoard.setSnorkelTile("Purple", x+1, y+1);
						}else if(snorkelBoard[x][y].getColour().equals("Green"))
						{
							gameBoard.setSnorkelTile("Green", x+1, y+1);
						} else if (snorkelBoard[x][y].getColour().equals("Stone"))
						{
							gameBoard.setStoneTile(x+1, y+1);
						}
						
						
					}
				}
				fr.close();
				reader.close();
				
			}catch(FileNotFoundException e)
			{
				System.out.println("something went wrong");
			}catch(IOException e){}
	}
	public void save()
	{
		try
		{
		FileWriter writer =new FileWriter(new File("saveClassic1.txt"));
		BufferedWriter bw= new BufferedWriter(writer);
		bw.write("Classic");
		bw.newLine();
		bw.write(new Integer(scoreLimit).toString());
		bw.newLine();
		bw.write(new Integer(numberOfStones).toString());
		bw.newLine();
		bw.write(new Integer(playerMode).toString());
		bw.newLine();
		bw.write(difficulty);
		bw.newLine();
		bw.write(currentPlayer);
		bw.newLine();
		bw.write(new Integer(numberOfMoves).toString());
		bw.newLine();
		
		for(int x=0; x<=6;x++)
		{
			for (int y=0;y<=6;y++)
			{
				bw.write(snorkelBoard[x][y].getColour()+" ");
			}
			bw.newLine();	
		}
		
		bw.close();
		writer.close();
		}catch(IOException e)
		{
			System.out.println("Something wrong accessing the file");
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
