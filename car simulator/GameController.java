//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 23 2014
//                      3.9
// Features: creats objects of cars/tracks, takes input from the user, generate blizzard or heat wawe
// allows car to move, does lots of checking, has the game loop so it allows to run the game until it's over
//activates cheat menu and use cheats, translates user input into index so we can set up new location for cars.
// I think that's it. It's a very long class
// Limitations: no real limitations, I used as much exceptions as possible to catch all the errors. Some things can be hard to see
// so you probablly might have to increase the size of the console. I wish we use colours but looks like we can do it only in GUI.
// logical errors haven't been found. 

// http://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html try - catch - exception
// it's better than having lots of checks. I don't really now how it works but I found it there and in the textbook. It's the same concept as in
//python by the way. So every time you do something wrong, it doesn't crush. 

//http://www.tutorialspoint.com/java/number_parseint.htm I use it to convert char/string input into integers


import java.util.Random;

public class GameController
{
	//initialization
	private ArticTrack artic_track;
	private DesertTrack desert_track;
	
	
	public GameController()
	{
		// creates tracks and sets cars to 0 position
		artic_track = new ArticTrack();
		desert_track = new DesertTrack();
		

		artic_track.setLocation(new SUV(), 0);
		desert_track.setLocation(new Sports(), 0);
	}
	
	

    public void start()
    {
    	// sets menu selection to empty char, welcome message is shown including some Basic rules of the game
		char menu_selection = ' ';
		
        System.out.println("Driver's start your engines!\n");
        System.out.println("Rules: \nIf SUV is stuck in blizzard, it won't move unless you are in AWD mode!");
        System.out.println("If Sport Car is hit by a heat wawe, gas consumption will increase by 2!");
		System.out.println("Game is going until a car reaches the end, or until at least 1 car has some fuel left");
        try {
        
        do
        {

   			// displays 2 tracks
			System.out.println("\nARTIC TRACK");
			artic_track.display();
			
			System.out.println("\nDESERT TRACK");
			desert_track.display();
			

			// code below is for suv movement input
			do {
			System.out.println("\nSUV driving options\n(a)ll wheel drive mode\n(d)rive normally\n(q)uit simulation\n");
			//prints options, then gets user input
			menu_selection = getUserSelection();
				

			if (menu_selection == 'a')
			{
				// all wheel drive mode, allows SUV to move during blizzard, debbuging message 
				
				move_suv(true);
				if (Debug.getOn())
				{
				Debug.info("Your choice: 'a', move_suv(true)");
				}

			}


			else if (menu_selection == 'd')
			{
				// normal drive, sets AWD to false,(see code below), debugging message
				move_suv(false);	
				if (Debug.getOn()) 
				{
				Debug.info("Your choice: 'd', move_suv(false)");
				}
			}


			else if (menu_selection == 'c')
			{
				//cheat menu
				CheatMenu();
				if (Debug.getOn())
				{
				Debug.info("Your choice: 'c', CheatMenu()");
				}	
			}

			else if (menu_selection == 'q')
			{
				//quit simulation
				System.exit(0);		
				if (Debug.getOn())
				{ 
				Debug.info("Your choice: 'q', System.exit(0)");
				}
			}
			}
			while (menu_selection != 'a' && menu_selection != 'd' && menu_selection != 'c' && menu_selection != 'q');
			//runs the loop until u until you enter the right letter (a,d,c,q)

			// code below is resposible for sports car movement input 
			do {
			System.out.println("\nSportscar driving options\n(d)rive normally\n(q)uit simulation\n");
			

			menu_selection = getUserSelection();
			
			if (menu_selection == 'd')
			{
				//drive normally
				
				if (Debug.getOn()) 
				{
				Debug.info("Your choice: 'd'");
				}
				
				//gets sport car from desert.
				Sports sportCar = desert_track.getSportCar();
				
				if (sportCar != null)
				{ // car object found

					// turns off heatwave, so it doesn't last forever
					if (desert_track.getHeatWave())
					{
						desert_track.setHeatWave(false);
					}
				
					// calculates new location based on travelled distance, and sets location to current location. I use it to wipe it's previus move
					int location = desert_track.getSportCarPosition();
					int new_location = location + sportCar.move(desert_track.getHeatWave());	
					

					// debuging message 
					if (Debug.getOn())
					{
					Debug.info("Old location of Sport Car: " + location + ", New location of Sport Car:" + new_location);
					}

					// wipes old location
					desert_track.setLocation(null, location);
				
					// sets new location
					desert_track.setLocation(sportCar, new_location);
				

					// sets heat wawe 
					if (!desert_track.getHeatWave() && ActOfNature(desert_track.heat_wave_chances))
					{
						desert_track.setHeatWave(true);

						//if (new_location>0)
						sportCar.move(desert_track.getHeatWave());
						//after big hit by a heat wawe, increases full consumption. 
					}
				}
			}
			

			else if (menu_selection == 'c')
			{
				//cheat menu
				CheatMenu();

				if (Debug.getOn())
				{
				Debug.info("Your Choice: 'c', Cheat Menu");
				}
			}
			
			else if (menu_selection == 'q')
			{
				//quit simulation
				
				System.exit(0);	

				if (Debug.getOn()) 
				{
				Debug.info("Your Choice: 'q', Quit Game");
				}
			}
			}
			while (menu_selection != 'd' && menu_selection != 'c' && menu_selection != 'q');
			//loop runs until you enter d,c or q
		}
		while (!artic_track.isWon() && !desert_track.isWon() && (!artic_track.getSUV().isEmpty() || !desert_track.getSportCar().isEmpty()));
		// Game loop, runs the whole thing until a car reaches the end, or if cars get out of fuel

		// checks simulation results, shows one of win messages
		if (artic_track.isWon())
		System.out.println("\nSUV won the Race!!!");


		else if (desert_track.isWon())
		System.out.println("\nSport won the Race!!!");


		else if (artic_track.isWon() && desert_track.isWon())
		System.out.println("\nBoth tracks won the Race!!!");
		

		else if (artic_track.getSUV().isEmpty() && desert_track.getSportCar().isEmpty())
		System.out.println("\nBoth cars are out of fuel. No one won the Race !!!");
		

		// print tracks one last time
		System.out.println("\nARTIC TRACK");
		artic_track.display();
			
		System.out.println("\nDESERT TRACK");
		desert_track.display();
		
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
    }
    

	    
    // convert track position character into track array index
    private int CharToIndex(char c)
    {		
		int i = Character.getNumericValue(c);
		
		if (i >= 1 && i <= 35)
		{
			return i-1;
		}
		else
		return 0;
	}
    

    
    // move suv
    private void move_suv(boolean awd_mode)
    {
		try {
		
		SUV suv = artic_track.getSUV();
		
		if (suv != null)
		{ // car object found


			if(awd_mode)
			{
				artic_track.setBlizzard(false);
			}
		
			// calc new location based on travelled distance
			int location = artic_track.getSUVPosition();
			int new_location = location + suv.move(artic_track.getBlizzard(), awd_mode);	
			
			if (Debug.getOn()) 
			{
				Debug.info("Old Location: " + location + ", new location: " + new_location);
			}
			
			// errases old location
			artic_track.setLocation(null, location);
			
			// set new location
			artic_track.setLocation(suv, new_location);
			

			// after being stuck, sets it to false so you can move in your next step
			if(artic_track.getBlizzard())
			{
				artic_track.setBlizzard(false);
			}
			

			// sets blizard to true
			if (!artic_track.getBlizzard() && ActOfNature(artic_track.blizzard_chances))
			{
				artic_track.setBlizzard(true);
			}
		}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
    

	// generates random weather, generates 10 number, 0 is one of them so, it's 10%
	// it works    
    private boolean ActOfNature(int probability)
    {
		Random generator = new Random();
		return (generator.nextInt(probability) == 0);
	}
    

	// cheat menu    
    private void CheatMenu()
    {
		try {
		
		System.out.println("\nCHEAT MENU");
		System.out.println("(0) Toggle debugging messages on/off\n(1) Change fuel of sports car\n(2) Change fuel of SUV car\n(3) Change location of sports car\n(4) Change location of suv car\n(5) Make a blizzard in the artic track\n(6) Make a heat wave in the desert track\n(Any other input) - do nothing\n");
				
		char menu_selection = getUserSelection();

		String input;
		// System.console().readLine(); I use to get input from the console. 
		//http://stackoverflow.com/questions/4644415/java-how-to-get-input-from-system-console used it as an example.
		// why JT didn't teach us how that before? 

		switch (menu_selection)
		{
			case '0':
				// debugging
				Debug.setOn(!Debug.getOn());
				System.out.println("Debug: " + Debug.getOn());
				break;
					
			case '1':
				// changes fuel of sports car
				System.out.print("(Sport Car) New fuel: ");

				input = System.console().readLine();
				desert_track.getSportCar().setFuel(Integer.parseInt(input));
				break;
				

			case '2':
				// changes fuel of suv car
				System.out.print("(SUV) New fuel: ");

				input = System.console().readLine();
				artic_track.getSUV().setFuel(Integer.parseInt(input));
				break;
				

			case '3':
				// changes location of sport car, errases old. Read the code, it explains itself.
				System.out.print("new sport car location: ");

				int new_sport_car_location = CharToIndex(getUserSelection());
				int current_sport_car_location = desert_track.getSportCarPosition();

				desert_track.setLocation(desert_track.getSportCar(), new_sport_car_location);
				desert_track.setLocation(null, current_sport_car_location);
				break;


			case '4':
				// changes locations of suv car, works as case 3
				System.out.print("new suv car location: ");

				int new_suv_location = CharToIndex(getUserSelection());
				int current_suv_location = artic_track.getSUVPosition();

				artic_track.setLocation(artic_track.getSUV(), new_suv_location);
				artic_track.setLocation(null, current_suv_location);
				break;
				

			case '5':
				// makes a blizzard in the artic track
				artic_track.setBlizzard(true);
				System.out.println("Causing a blizzard in the artic track");
				break;
				

			case '6':
				// makes a heat wave in the desert track
				desert_track.setHeatWave(true);
				System.out.println("Causing a heat wave in the desert track");
				break;
				
			default:
			//I used defaul because of that http://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html 
			//just in case we get a wrong input. 
			// if intup doens't equel to one of the cases, there is no default value
			//In theory it should minimize amount of errors
		}
		
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
    

    
    // get first char of user input
    private char getUserSelection()
    {
    	//inirilization
		char user_input = ' ';
		char next_line = ' ';
		
		System.out.print("Enter selection: ");
		
		try
		{	
			//Reads the next byte of data from the input stream. 
			user_input = (char) (System.in.read());
			

		// without this part bellow, if cheat menu is activated, you won't be able to use cheats, and input gets screwed up
			do
			{
				next_line = (char) (System.in.read());
			}
			while (next_line!= '\n');

		}
		catch (java.io.IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		//returns input 
		return user_input;
	}
}
