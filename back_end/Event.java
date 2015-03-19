/*Event.java
	This class will model the events read in through the event file
*/
import java.util.ArrayList;
import java.util.List;
public class Event{
	private String title;
	//returns the name of the event
	public String getEventName(){
		return this.title;
	}
	//returns the number of available tickets
	public int countTickets(){
		//dummy return
		return 0;
	}
	//returns the price of the event
	public float getPrice(){
		//Dummy return
		return 0.0f;
	}
}