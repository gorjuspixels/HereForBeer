import org.apache.commons.lang.RandomStringUtils;
import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class BackEndTest {

  private final String GREEN = (char)27 + "[32m";
  private final String RED = (char)27 + "[31m";

  private String transactionFileName;
  private String userFileName;
  private BackEnd backEnd;
  private PrintWriter userAccountWriter;

  @Test
  public void testCreateUser() {
    String[] transaction = new String[]{
        "01 sam             FS 000000100",
        "00                    000000000"
    };
    String description = "should create user same with FS user type and 100 as credit";
    setupTest(transaction);
    backEnd.getUserAccounts().save(userAccountWriter);

    try {
      Assert.assertTrue(description,
          validateUser("sam             FS 000000100", backEnd.getUserAccounts()));
      System.out.println(GREEN + description + " - passed");
    } catch(AssertionError e) {
      System.out.println(RED + description + " - failed");
      throw e;
    }
  }

  @Test
  public void testSuccessDeleteUser() {
    String[] transaction = new String[]{
        "01 sam             FS 000000100",
        "02 sam             FS 000000100",
        "00                    000000000"
    };
    String description = "should delete user when code 02 and user exists";
    setupTest(transaction);
    backEnd.getUserAccounts().save(userAccountWriter);

    try {
      Assert.assertEquals(description, 0, backEnd.getUserAccounts().size());
      System.out.println(GREEN + description + " - passed");
    } catch(AssertionError e) {
      System.out.println(RED + description + " - failed");
      throw e;
    }
  }

  private void setupTest(String[] transaction) {
    transactionFileName = writeTransactionFile(transaction);
    backEnd = new BackEnd(transactionFileName);
  }

  @Before
  public void executedBeforeEach() {
    userFileName = "UserAccounts" + RandomStringUtils.randomAlphanumeric(20).toUpperCase() + ".txt";
    try {
      userAccountWriter = new PrintWriter(userFileName, "UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  @After
  public void executedAfterEach() {
    File transactions = new File(transactionFileName);
    transactions.delete();
    File userAccounts = new File(userFileName);
    userAccounts.delete();
    userAccountWriter.close();
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