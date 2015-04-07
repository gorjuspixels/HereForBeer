//UserAccount_Holder.java
//This class will serve to hold a list of all the User accounts
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class UserAccount_Holder{
	private List<UserAccount> user_accounts;

  public UserAccount_Holder() {
    user_accounts = new ArrayList<UserAccount>();
  }

	//Returns the list of user accounts
	public UserAccount get(int index){
		return this.user_accounts.get(index);
	}
	public UserAccount add(UserAccount userAccount) {
    user_accounts.add(userAccount);
		return userAccount;
	}

  /**
   * Returns number of elements in user_accounts
   * @return int size of user_accounts
   */
  public int size() {
    return this.user_accounts.size();
  }

  /**
   * Removes and returns user account from the list
   * @param index - position of the element to remove
   * @return UserAccount that was removed
   */
  public UserAccount remove(int index) {
    return this.user_accounts.remove(index);
  }

	public void save(PrintWriter writer) {
		for (UserAccount userAccount : user_accounts) {
      if (userAccount != null && userAccount.getUsername() != null) {
        writer.println(userAccount.toString());
      }
    }

    writer.close();
	}

  /**
   * Finds a user account based on its username
   * @param username - user's username
   * @return int index of UserAccount element in the array
   */
  public int find(String username) {
    int userIndex = -1;
    for (int i=0; i<user_accounts.size(); i++) {
      if (user_accounts.get(i).getUsername().equals(username)) {
        userIndex = i;
        break;
      }
    }

    return userIndex;
  }

  /**
   * Sets UserAccount at an index in the array
   * @param userIndex - index to the element at
   * @param userAccount - UserAccount object to set
   */
  public void set(int userIndex, UserAccount userAccount) {
    user_accounts.set(userIndex, userAccount);
  }
}