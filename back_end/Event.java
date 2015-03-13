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
	//saves the event
	public void save(){

	}
	//returns a list of all the events that were on the event file
	public static List<Event> getAllEvents(){
		//dummy return
		List<Event> l = new ArrayList<Event>();
		return l;
	}
	//returns an event as specified through the event title
	public static Event get(String title){
		//dummy return
		return new Event();
	}

}