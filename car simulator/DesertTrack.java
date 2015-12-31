//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 17 2014
//                      1.3
// Features: allows to set/get heatwave, get car and it's location
// Limitations: I have exception so it's tough to crash the game. 

public class DesertTrack extends Track
{
	//initialization 
	public static final int heat_wave_chances = 10;
	private boolean heat_wave;
	

	// allows to get heat wawe 
	public boolean getHeatWave()
	{
		return heat_wave;
	}
	
	
	// sets heat wave, if true shows the message 
	public void setHeatWave(boolean heatWawe)
	{
		heat_wave = heatWawe;
		
		if (heat_wave)
		System.out.println("You car was hit by heat wawe. Gas consumption increased by 2");
		//heat_wave=false;
	}
	


	// finds car location and it allows to get it's location. in other case we get the error
	public Sports getSportCar() throws Exception
	{
		Sports sport = null;
		
		// find current car position
		for (int i=0; i < this.SIZE; i++)
		{
			Car [] car_array = this.getTrack();
			sport = (Sports) car_array[i];
			
			if (sport != null)
			return sport;
		}
		
		throw new Exception("Sport Car not found");
	}
	

	// gets sport car potion, if can't do so u get this lovely error message
	public int getSportCarPosition() throws Exception
	{
		for (int i=0; i < this.SIZE; i++)
		{
			Car [] car_array = this.getTrack();
			Sports sport = (Sports) car_array[i];
			
			if (sport != null)
			return i;
		}
		
		throw new Exception("Sport Car not found");
	}
}
