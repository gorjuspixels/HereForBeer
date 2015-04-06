/*UserAccount.java
	This class will model the users to be modified in the program
*/
public class UserAccount{
	private String username;
	private String type;
	private float credit;

	//returns the user's username
	public String getUsername(){
		return this.username;
	}
	//returns the user's type
	public String getType(){
		return this.type;
	}
	//returns the user's available credits
	public float getCredit(){
		return this.credit;
	}
	//sets the user's username
	public void setUsername(String username){
		this.username = username;
	}
	//Sets the user's type
	public void setType(String type){
		this.type = type;
	}
	//Sets the user's credits
	public void setCredit(float credit){
		this.credit = credit;
	}
	//Returns a User object, found by their username
	public UserAccount get(String username){
		//Place holder code
		return this;
	}

  /**
   * Converts UserAccount object to UserAccount writable string in format UUUUUUUUUUUUUUU_TT_CCCCCCCCC
   * @return formatted user account string
   */
  public String toString() {
    String s = "";
    for (int i = 0; i < 15; i++) {
      if (i < this.username.length()) {
        s += this.username.charAt(i);
      } else {
        s += " ";
      }
    }

    s += " ";
    s += this.type;
    s += " ";

    String tempCredit = Float.toString(this.credit).replace(".0", "");
    int length = tempCredit.length();
    for (int i = 0; i < 9 - length; i++) {
      tempCredit = "0" + tempCredit;
    }
    s += tempCredit;

    return s;
  }

}
