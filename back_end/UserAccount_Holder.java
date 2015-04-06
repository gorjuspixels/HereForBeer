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

	public void save(PrintWriter writer) {
		for (UserAccount userAccount : user_accounts) {
      writer.println(userAccount.toString());
    }

    writer.close();
	}
}