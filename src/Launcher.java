import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher
{
	private Scanner scan;
	private String menuInput;
	int scorelimit=6;
	int numberOfStones=-1;
	int turnTimer=0;
	public Launcher()
	{
	scan = new Scanner(System.in);
	mainmenu();
	}
	private void mainmenu()
	{
		
		do
		{
		System.out.println("Choose one of the following options:");
		System.out.println("1.Start Game");
		System.out.println("2.Resume Game");
		System.out.println("3.View Achievements");
		System.out.println("4.View High-Scores");
		System.out.println("5.Quit Game");
		
	    
		menuInput = scan.next();
		String mode="";
		String playerMode="";
		String difficulty="";
		switch(menuInput)
     	{
     		case "1":
     		{
     			
     			
     			while(!(playerMode.equals("1"))&& !(playerMode.equals("2")) )
     			{
     				System.out.println("Please give me the player mode: (1)1-Player Mode or (2)2-Player Mode");
     				playerMode=scan.next();
     			}
     			if(playerMode.equals("1"))
     			{
     				while(!(difficulty.equals("easy"))&& !(playerMode.equals("hard")) )
     				{
     					System.out.println("Choose Difficulty Level of AI: easy or hard");
     					difficulty=scan.next();
     				}
     			}
     			while (!(mode.equals("classic")) && !(mode.equals("speed")))
     			{
     			System.out.println("Please give me the game mode: Classic(classic) or Speed Snorkels(speed)");
     		    mode = scan.next();
     			}
     			
     		    setupGame(mode,playerMode,difficulty);
     		    //set player mode vs Human or AI
     			break;
     		}
     		case "2":
     		{
     			System.out.println("Resuming the game");
     			try
     			{
     				FileReader fr = new FileReader("saveClassic1.txt");
     				BufferedReader reader= new BufferedReader(fr);
     				String line =reader.readLine();
     				Scanner scan =  new Scanner(line);
     				if (scan.next().equals("Classic"))
     				{
     					line=reader.readLine();
     					scan =  new Scanner(line);
     					scorelimit=Integer.parseInt(scan.next());
     					line=reader.readLine();
     					scan =  new Scanner(line);
     					numberOfStones=Integer.parseInt(scan.next());
     					line=reader.readLine();
     					scan =  new Scanner(line);
     					playerMode=scan.next();  
     					line=reader.readLine();
     					scan=new Scanner(line);
     					if(scan.hasNext())
     					{
     						difficulty=scan.next();
     					}
     					fr.close();
     					reader.close();
     					ClassicMode game= new ClassicMode(scorelimit,numberOfStones,0,playerMode,difficulty,true);	
     				}else if (scan.next().equals("Speed"))
     				{
     					
     					SpeedSnorkelMode game= new SpeedSnorkelMode(scorelimit,numberOfStones,turnTimer,playerMode,difficulty,true);
     				}
     			}catch(FileNotFoundException e)
     			{
     				System.out.println("There is no saved game");
     			}catch(IOException e){}
     			break;
     		}
     		case "3":
     		{
     			System.out.println("These are your achievements");
     			break;

     		}
     		case "4":
     		{
     			System.out.println("These are your highscores");
     			break;

     		}
     		case "5":
     		{
     			System.out.println("Quitting..");
     			break;
     		}
     		default:
     		{
     			System.out.println("Invalid command, try again");
     		}
     	
     	}
	    
		}while(!menuInput.equals("5"));
	}
	 private void setupGame(String mode,String playerMode,String difficulty)
	 {
		 
		 scan= new Scanner(System.in);
		 switch (mode)
		 { 
		 	case "classic":
		 	{
		 		 scorelimit=6;
		 		 numberOfStones=-1;
		 		 turnTimer=0;
		 		settings();
		 		ClassicMode game= new ClassicMode(scorelimit,numberOfStones,0,playerMode,difficulty,false);

		 		break;
		 	}
		 	case "speed":
		 	{	
		 		scorelimit=6;
		 		numberOfStones=-1;
		 		turnTimer=0;
		 		settings();
		 		while(turnTimer!=5 && turnTimer!=10 && turnTimer!=15)
		 		{
		 		System.out.println("Set the turn timer 5-10-15 seconds:");
		 	    try
		 	    {
		 	    scan = new Scanner(System.in);
		 		turnTimer=scan.nextInt();
		 		
		 	    }catch(InputMismatchException e){}
		 	    }
		 		SpeedSnorkelMode game= new SpeedSnorkelMode(scorelimit,numberOfStones,turnTimer,playerMode,difficulty,false);
		 		
		 		break;
		 	}
		 	default:
		 	{
		 		System.out.println("There is no such game mode.");
		 	}
		 	
		 }
		 
	 }
	 private void settings()
	 {
		 while(scorelimit<1 || scorelimit>5)
	 		{
	 		System.out.println("Set score limit (1-5):");
	 		try{
	 		scan = new Scanner(System.in);
	 		scorelimit=scan.nextInt();
	 		}catch(InputMismatchException e){}
	 		}
	 		while (numberOfStones<0 || numberOfStones>3)
	 		{
	 		System.out.println("Set number of stones up to 3:");
	 		try{
	 		scan = new Scanner(System.in);
	 		numberOfStones=scan.nextInt();
	 		}catch(InputMismatchException e){}
	 		}
	 }
}
