//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 17 2014
//                      1.0
// Features: Parent class Car 
// Limitations: James knows what it does

/*
  Author:  James Tam
  Version: March 3, 2014

  Features:
  * Tracks and manages information for appearance and fuel 
  * capacity.

 */

public class Car
{
    public static final int STARTING_FUEL = 40;
    public static final int STANDARD_DISTANCE = 2;
    public static final int CONSUMPTION_RATE = 2;

    private char appearance;
    private int fuel;

    public Car()
    {
 	   setAppearance('c');
	   fuel = STARTING_FUEL;
    }

    // As the car moves, this class or child classes can specify          
    // for that type of car how much fuel to consume.
    protected void consumeFuel(int amount)
    {
	   if (amount < 0)
	       fuel = 0;
	   else
	       fuel = fuel - amount;
    }

    public int getFuel()
    {
	   return(fuel);
    }

    // Allows external queries of the fuel state of a particular   
    // car.
    public boolean isEmpty()
    {
	   if (fuel <= 0)
	       return(true);
	   else
	       return(false);
    }

    // Car internally knows how much fuel to use and how far to  
    // move. However the actual movement in the virtual track 
    // must be done by another class hence
    // this method must communicate to the caller how far it 
    // moves.
    //
    // If this method is overriden then all child class methods 
    // must also display the same debugging messages shown 
    // below.
    public int move()
    {
	   consumeFuel(CONSUMPTION_RATE);
	   System.out.println("Current fuel: " + fuel);
	   System.out.println("Fuel use: " + CONSUMPTION_RATE);
	   return(STANDARD_DISTANCE);
    }

    public void setAppearance(char anAppearance)
    {
	   appearance = anAppearance;
    }

    public void setFuel(int newFuel)
    {
	   if (newFuel < 0)
	       System.out.println("Fuel cannot be set to " +
                               "negative value");
	   else
	       fuel = newFuel;
    }

    public String toString() 
    {
	   String s = "";
	   s = s + appearance;
	   return(s);
    }
}