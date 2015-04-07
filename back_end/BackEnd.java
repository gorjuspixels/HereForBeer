import java.io.*;
public class BackEnd {
  private static final String USER_ACCOUNTS = "UserAccounts.txt";
  private static final String AVAIL_TICKETS = "AvailableTickets.txt";
  private static final String TRANSACTION_FILE = "transactions.etf";
  private static FileReader accountFileReader;
  private static FileReader ticketFileReader;
  private String transactionFile;
  private UserAccount_Holder accounts;

  /**
   * Creates BackEnd class with specified transactional input file
   * @param s - transactional input file
   */
  public BackEnd(String s) {
    transactionFile = s;
    processTransactions();
  }

  /**
   * Empty constructor
   */
  public BackEnd() {
    processTransactions();
  }

  private void processTransactions() {
    //Creates holder classes for the events, and accounts
    accounts = new UserAccount_Holder();
    Event_Holder events = new Event_Holder();
    if (transactionFile == null) {
      transactionFile = TRANSACTION_FILE;
    }

		/*TODO:Read the old current user accounts file
		 *     Create a user object for each user
		 *     Add each user to a list, and store in UserAccount_Holder class
		*/
    accountFileReader = initializeFile(USER_ACCOUNTS);


		/*TODO: Read the old available tickets file
		 *      Create an Event class for each event in the file
		 *      Add each event to a list, and store in the Event_Holder class
		*/
    ticketFileReader = initializeFile(AVAIL_TICKETS);


    //Reads the merged daily transaction file
    try{
      FileReader transaction_fr = new FileReader(transactionFile);


      //Reads the transaction file line by line
      try{
        BufferedReader br = new BufferedReader(transaction_fr);

        //Applies the transactions
        while(br.ready()){
          String transactionString = br.readLine();
          Transaction transaction = new Transaction(transactionString);

          if (transaction.getCode().equals("00") || transaction.getCode().equals("01")
              || transaction.getCode().equals("02") || transaction.getCode().equals("06")) {
            // 00 = end of session
            // 01 = create user
            // 02 = delete user
            // 06 = add credit
            if (transaction.getCode().equals("00")) {
              continue;
            }

            UserAccount userAccount = new UserAccount();
            if (transaction.getCode().equals("01")) {
              userAccount.setUsername(transaction.getUsername());
              userAccount.setType(transaction.getUserType());
              userAccount.setCredit(transaction.getCredit());
              accounts.add(userAccount);
            } else if (transaction.getCode().equals("02")) {
              boolean userExists = false;
              int userIndex = -1;
              for (int i=0; i<accounts.size(); i++) {
                if (accounts.get(i).getUsername().equals(transaction.getUsername())) {
                  userExists = true;
                  userIndex = i;
                  break;
                }
              }

              if (!userExists) {
                System.out.println("ERROR: User doesn't exist. " + transactionString);
                return;
              }

              accounts.remove(userIndex);
            }



//            this.setUsername(transaction.substring(3, 15).replaceAll("\\s+", ""));
//            this.setUserType(transaction.substring(19, 2));
//            this.setCredit(Float.valueOf(transaction.substring(22, 9).replaceFirst("^0+(?!$)", "")));
          } else if (transaction.getCode().equals("03") || transaction.getCode().equals("04")) {
            // 03 = sell
            // 04 = buy

//            this.setEventName(transaction.substring(3, 19).trim());
//            this.setSellerName(transaction.substring(23, 13).trim());
//            this.setTicketNum(Integer.valueOf(transaction.substring(37, 3).replaceFirst("^0+(?!$)", "")));
//            this.setTicketPrice(Integer.valueOf(transaction.substring(41).replaceFirst("^0+(?!$)", "")));
          } else if (transaction.getCode().equals("05")) {
            // 05 = refund
//            this.setUsername(transaction.substring(3, 15).replaceAll("\\s+", ""));
//            this.setSellerName(transaction.substring(19, 15).replaceAll("\\s+", ""));
//            this.setCredit(Float.valueOf(transaction.substring(35, 9).replaceFirst("^0+(?!$)", "")));
          }
					/*TODO: Create a new Transaction object with the information from the string
					 *      Create/modify the events/users if need be
					 */




        }
      }
      catch(IOException e){
        System.out.println("ERROR: An error occured while processing the transaction file");
      }

    }
    catch(FileNotFoundException e){
      System.out.println("ERROR: transactions.etf not found");
    }




    //Generate the new user accounts file
//    accounts.save();

    //Generate the new available tickets file
    events.save();
  }



  public static void main(String[] args) {

		new BackEnd();
	}


  /**
   * Initializes required files
   * @param fileName Name of the file to initialize
   * @return FileReader object
   */
  private static FileReader initializeFile(String fileName) {
    FileReader fileReader = null;
    try {
      File file = new File(fileName);
      if(!file.exists()) {
        file.createNewFile();
      }

      fileReader = new FileReader(file);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return fileReader;
  }

  public UserAccount_Holder getUserAccounts() {
    return accounts;
  }
}
