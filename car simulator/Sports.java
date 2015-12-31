//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 17 2014
//                      1.5
// Features: allows car to move, consumes fuel, checks for heat wave, sets car's look and gas 
// Limitations: no real limitations

public class Sports extends Car
{
	// initialization of fuel and standard movement 
	public static final int STARTING_FUEL = 30;
	public static final int STANDARD_DISTANCE = 3;
	
	// --------------------------------
	
	public Sports()
	{
		// sets appearance and fuel
		super.setAppearance('P');
		super.setFuel(STARTING_FUEL);

		System.out.println("Sport's Car initial fuel: " + super.getFuel() + "\n");
	}
	
	// --------------------------------
	
	public int move(boolean heat_wave)
	{
		int fuel_used;

		// checks if gas >1, I use it to hide stats when its out of fuel 
		if (getFuel()>=1){

			// checks if heat wawe is one, increases consumption
			if (heat_wave)
			{
			fuel_used = CONSUMPTION_RATE * 2;
			}


			// in other case it's normal 
			else
			{
			fuel_used = CONSUMPTION_RATE;
			}
			
			
			//consumes fuel and shows some basic stats and returns distance 
			consumeFuel(fuel_used);
			if (getFuel()>=1){
			System.out.println("Current fuel: " + getFuel());
			System.out.println("Fuel use: " + fuel_used);
			}
			
			return STANDARD_DISTANCE;
		}

		// if it's out of gas, doesn't move anywhere and shows the message
		else{
		System.out.println("Sport Car is out of fuel, you can't go futher");
		return(0);
		}
	}
}
