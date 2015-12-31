//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 17 2014
//                      1.5
// Features: allows car to move, consumes fuel, checks for blizzard and if AWD is on, sets car's look and gas 
// Limitations: no real limitations

public class SUV extends Car
{
	// initialize movemnet speed and initial fuel
	public static final int CONSUMPTION_RATE = 3;
	public static final int STARTING_FUEL = 50;
	public static final int AWD_MODE_DISTANCE = 1;
	

	public SUV()
	{
		//sets appearance and fuel, print's initial amount of fuel
		super.setAppearance('V');
		super.setFuel(STARTING_FUEL);

		System.out.println("\nSUV's initial fuel: " + super.getFuel());
	}
	

	public int move(boolean blizzard, boolean awd_mode)
	{
		// I use fuel checks just to make sure that you won't see any stats if u will have 0 fuel 
		if (getFuel()>=1){

		// consumes fuel, shows stats 
		consumeFuel(CONSUMPTION_RATE);

		// doesn't show if gas is below 0 
		if (getFuel()>=1){
		System.out.println("Current fuel: " + getFuel());
		System.out.println("Fuel use: " + CONSUMPTION_RATE);
		}
		

		if (blizzard || awd_mode)
		{
			// checks if blizzard and awd is on, suv can move
			System.out.println("Blizzard is going, you are in AWD mode. You move slowly");
			return AWD_MODE_DISTANCE;
		}


		else if (blizzard && !awd_mode)
		{	
			//if blizzard is on and you are not in AWD, suv is stuck and you have to switch to AWD to move forward
			System.out.println("Blizzard is going. You are stuck. Switch to AWD mode to move forward");
			return 0;
		}


		else
		{
			// if in normal and no blizzard, moves in normal rate
			return STANDARD_DISTANCE;
		}
		}

		// in case gas is 0 or below
		else{
			System.out.println("SUV is out of gas, you can't move forward");
			return(0);
		}
	}
}
