//Event_Holder.java
//This class will server to hold a list of all the events
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Event_Holder {
  private List<Event> events;

  public Event_Holder() {}

  public Event_Holder(String fileName) {
    events = new ArrayList<Event>();
    File file = new File(fileName);

    try {
      Scanner scanner = new Scanner(file);

      //now read the file line by line...
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        Event event = new Event(line);
        events.add(event);
      }
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    }
  }

	//Returns the list of events
	public List<Event> get(){
		return this.events;
	}


  /**
   * Saves data to AvailableTickets.txt file
   * @param writer - PrintWriter to write to (usually file)
   */
  public void save(PrintWriter writer) {
    for (Event event : events) {
      if (event != null && event.getEventName() != null) {
        writer.println(event.toString());
      }
    }

    writer.close();
  }
}