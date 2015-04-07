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
}