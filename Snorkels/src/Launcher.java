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
	    
		switch(menuInput)
     	{
     		case "1":
     		{
     			String mode="";
     			while (!(mode.equals("classic")) && !(mode.equals("speed")))
     			{
     			System.out.println("Please give me the game mode: Classic(classic) or Speed Snorkels(speed)");
     		    mode = scan.next();
     			}
     		    setupGame(mode);
     		    //set player mode vs Human or AI
     			break;
     		}
     		case "2":
     		{
     			System.out.println("Resuming the game");
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
	 private void setupGame(String mode)
	 {
		 
		 scan= new Scanner(System.in);
		 switch (mode)
		 { 
		 	case "classic":
		 	{
		 		settings();
		 		ClassicMode game= new ClassicMode(scorelimit,numberOfStones,0);
		 		 scorelimit=6;
		 		 numberOfStones=-1;
		 		 turnTimer=0;
		 		break;
		 	}
		 	case "speed":
		 	{	
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
		 		SpeedSnorkelMode game= new SpeedSnorkelMode(scorelimit,numberOfStones,turnTimer);
		 		scorelimit=6;
		 		numberOfStones=-1;
		 		turnTimer=0;
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
