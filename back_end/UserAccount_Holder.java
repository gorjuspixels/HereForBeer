//UserAccount_Holder.java
//This class will serve to hold a list of all the User accounts
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAccount_Holder{
	private List<UserAccount> user_accounts;

  public UserAccount_Holder() {
    user_accounts = new ArrayList<UserAccount>();
  }

  public UserAccount_Holder(String userFileName) {
    user_accounts = new ArrayList<UserAccount>();
    File file = new File(userFileName);

    try {
      Scanner scanner = new Scanner(file);

      //now read the file line by line...
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        UserAccount userAccount = new UserAccount(line);
        user_accounts.add(userAccount);
      }
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    }
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

  /**
   * Saves data to UserAccount.txt file
   * @param writer - PrintWriter to write to (usually file)
   */
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