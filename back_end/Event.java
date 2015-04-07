/*Event.java
	This class will model the events read in through the event file
*/
public class Event {
	private String title;
  private String sellerName;
  private float price;
  private int count;

  public Event(String data) {
    title = "";

    for (int i = 0; i < 19; i++) {
      title += data.charAt(i);
    }
    title = title.replaceAll(" ", "");

    sellerName = "";
    for (int i = 20; i < 33; i++) {
      sellerName += data.charAt(i);
    }

    count = 0;
    String tempCount = "";
    for (int i = 34; i < 37; i++) {
      tempCount += data.charAt(i);
    }
    tempCount = tempCount.replaceFirst("^0+(?!$)", "");
    count = Integer.valueOf(tempCount);

    price = 0f;
    String tempPrice = "";
    for (int i = 38; i < 44; i++) {
      tempPrice += data.charAt(i);
    }
    tempPrice = tempPrice.replaceFirst("^0+(?!$)", "");
    price = Float.valueOf(tempPrice);
  }

  public Event() {}

  /**
   * Sets event title
   * @param name - name of the event
   */
  public void setEventName(String name) {
    this.title = name;
  }

  /**
   * Sets seller's name
   * @param name - seller's name
   */
  public void setSellerName(String name) {
    this.sellerName = name;
  }

  /**
   * Sets event price
   * @param price - event's price
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * Sets ticket count
   * @param count - number of available tickets
   */
  public void setCount(int count) {
    this.count = count;
  }

  /**
   * Returns the name of the event
   * @return event name
   */
	public String getEventName(){
		return this.title;
	}

  /**
   * Returns the number of available tickets
   * @return number of tickets
   */
	public int countTickets(){
		return count;
	}

  /**
   * Returns the price of the event
   */
	public float getPrice(){
		return price;
	}

  /**
   * Returns seller's username
   * @return String seller's username
   */
  public String getSeller() {
    return sellerName;
  }

  /**
   * Converts Event object to Event writable string in format EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
   * @return formatted event string
   */
  public String toString() {
    String s = "";
    String eventName = this.getEventName();
    for (int i = 0; i < 19; i++) {
      if (i < eventName.length()) {
        s += eventName.charAt(i);
      } else {
        s += " ";
      }
    }

    s += " ";
    String sellerName = this.getSeller();
    for (int i = 0; i < 13; i++) {
      if (i < sellerName.length()) {
        s += sellerName.charAt(i);
      } else {
        s += " ";
      }
    }

    s += " ";
    String count = Integer.toString(this.countTickets());
    for (int i = 0; i < 3 - count.length(); i++) {
      s += "0";
    }
    s += count;

    s += " ";
    String tempCredit = Float.toString(this.getPrice()).replace(".0", "");
    int length = tempCredit.length();
    for (int i = 0; i < 6 - length; i++) {
      tempCredit = "0" + tempCredit;
    }
    s += tempCredit;

    return s;
  }
}