import java.util.ArrayList;
import java.util.List;
public class Event{
	private String title;
	public String getEventName(){
		return this.title;
	}
	public int countTickets(){
		//dummy return
		return 0;
	}
	public float getPrice(){
		//Dummy return
		return 0.0f;
	}
	public void save(){

	}
	public static List<Event> getAllEvents(){
		//dummy return
		List<Event> l = new ArrayList<Event>();
		return l;
	}
	public static Event get(String title){
		//dummy return
		return new Event();
	}

}