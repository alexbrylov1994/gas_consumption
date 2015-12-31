//               Full assignment 3
//                Oleksiy Brylov
//                   10123597 
//                   CPSC 233
//             Lecture 1, Tutorial 02
//              Version March 17 2014
//                      1.0
// Features: uses it for turning on/off debbuging and debuging messages
// Limitations: James knows what it does
/*
  Author:  James Tam
  Version: May 3, 2014

  The sole purpose of this class is track if the program is in 
  debugging mode.

 */
public class Debug
{
    private static boolean on = false;
    
    public static boolean getOn() {  return(on); }
    
    public static void setOn(boolean onValue) { on = onValue; }
    
    // i use it to show debug information.  
    public static void info(String message)
    {
		System.out.println("# debug: " + message);
	  }
}
