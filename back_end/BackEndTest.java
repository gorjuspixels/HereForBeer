import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class BackEndTest {

  @Test
  public void testMain() throws Exception {
    String createUser = "01 sam             FS 000000100";
    String[] transaction = { createUser };
    String transactionFileName = writeTransactionFile(transaction);
    BackEnd backEnd = new BackEnd(transactionFileName);

    PrintWriter writer = new PrintWriter("UserAccounts1234.txt", "UTF-8");
    backEnd.getUserAccounts().save(writer);

    Assert.assertTrue("should create user same with FS user type and 100 as credit",
        validateUser("sam             FS 000000100", backEnd.getUserAccounts()));

    File transactions = new File(transactionFileName);
    transactions.delete();
  }

  /**
   * Validates created user against proper user record
   * @param s - Properly formatted user record
   * @param userAccounts - UserAccount holder to get users from
   * @return true or false if userAccounts are valid
   */
  private boolean validateUser(String s, UserAccount_Holder userAccounts) {
    String[] users = s.split("\n", -1);
    for (int i = 0; i<users.length; i++) {
      String name = users[i].substring(0, 15).replaceAll(" ", "");
      String type = users[i].substring(16, 18);
      Float credit = Float.valueOf(users[i].substring(19));
      if (!name.equals(userAccounts.get(i).getUsername())) {
        Assert.fail("\nExpected username: " + name + "\nActual username: " + userAccounts.get(i).getUsername() + "\n");
        return false;
      } else if (!type.equals(userAccounts.get(i).getType())) {
        return false;
      } else if (credit != userAccounts.get(i).getCredit()) {
        return false;
      }
    }
    return true;
  }

  private String writeTransactionFile(String[] transactions) {
    PrintWriter writer = null;
    String fileName = "transactions" + RandomStringUtils.randomAlphanumeric(20).toUpperCase() + ".etf";
    try {
      writer = new PrintWriter(fileName, "UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    for( String s: transactions ) {
      if (writer != null) {
        writer.println(s);
      }
    }
    if (writer != null) {
      writer.close();
    }
    return fileName;
  }
}