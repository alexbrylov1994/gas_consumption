//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 17 2014
//                      1.3
// Features: allows to set/get blizzard, get's SUV car and it's position 
// Limitations: I'm using exception so you can't do something wrong there. 


public class ArticTrack extends Track
{
	// initialization
	public static final int blizzard_chances = 10;
	private boolean blizzard;
	
	// --------------------------------
	
	// allows to get blizzard
	public boolean getBlizzard()
	{
		return blizzard;
	}
	

	
	// sets blizzard and shows the message 
	public void setBlizzard(boolean bliz)
	{
		blizzard = bliz;
	
		if (blizzard)
			System.out.println("SUV is stuck in blizzard, Switch to AWD to move forward");
	}
	


	// gets SUV, checks the array, it not found the error message appears
	public SUV getSUV() throws Exception
	{
		SUV suv = null;
		
		for (int i=0; i < this.SIZE; i++)
		{
			Car [] car_array = this.getTrack();
			suv = (SUV) car_array[i];
			
			if (suv != null)
			return suv;
		}
		
		throw new Exception("SUV not found");
	}
	

	
	// gets SUV's position, shows the error if can't do it
	public int getSUVPosition() throws Exception
	{
		for (int i=0; i < this.SIZE; i++)
		{
			Car [] car_array = this.getTrack();
			SUV suv = (SUV) car_array[i];
			
			if (suv != null)
			return i;
		}
		
		throw new Exception("SUV not found");
	}
}
