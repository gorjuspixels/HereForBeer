import java.io.*;
public class BackEnd {
  private static final String USER_ACCOUNTS = "UserAccounts.txt";
  private static final String AVAIL_TICKETS = "AvailableTickets.txt";
  private static final String TRANSACTION_FILE = "transactions.etf";
  private static FileReader accountFileReader;
  private static FileReader ticketFileReader;
  private String transactionFile;
  private String ticketFile;
  private String userFile;
  private UserAccount_Holder accounts;
  private Event_Holder events;
  private PrintStream consoleWriter;

  /**
   * Creates BackEnd class with specified transactional input file
   * @param s - transactional input file
   */
  public BackEnd(String s) {
    transactionFile = s;
    processTransactions();
  }

  /**
   * Creates BackEnd class with specified file inputs
   * @param transactionFile - transaction input file
   * @param ticketFile - available ticket input file
   * @param userFile - available ticket input file
   * @param consoleWriter - writer to output console log to
   */
  public BackEnd(String transactionFile, String ticketFile, String userFile, PrintStream consoleWriter) {
    this.transactionFile = transactionFile;
    this.ticketFile = ticketFile;
    this.userFile = userFile;
    this.consoleWriter = consoleWriter;
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
    if (transactionFile == null) {
      transactionFile = TRANSACTION_FILE;
    }
    if (ticketFile == null) {
      ticketFile = AVAIL_TICKETS;
    }
    if (userFile == null) {
      userFile = USER_ACCOUNTS;
    }

    if (consoleWriter == null) {
      consoleWriter = System.out;
    }

    accountFileReader = initializeFile(userFile);
    ticketFileReader = initializeFile(ticketFile);
    accounts = new UserAccount_Holder(userFile);
    events = new Event_Holder(ticketFile);


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


            if (transaction.getCode().equals("01")) {
              UserAccount userAccount = new UserAccount();
              userAccount.setUsername(transaction.getUsername());
              userAccount.setType(transaction.getUserType());
              userAccount.setCredit(transaction.getCredit());
              accounts.add(userAccount);
            } else if (transaction.getCode().equals("02")) {
              int userIndex = accounts.find(transaction.getUsername());

              if (userIndex == -1) {
                consoleWriter.println("ERROR: User doesn't exist. " + transactionString);
                return;
              }

              accounts.remove(userIndex);
            } else if (transaction.getCode().equals("06")) {
              int userIndex = accounts.find(transaction.getUsername());

              if (userIndex == -1) {
                consoleWriter.println("ERROR: User doesn't exist. " + transactionString);
                return;
              }

              UserAccount userAccount = accounts.get(userIndex);
              userAccount.setCredit(userAccount.getCredit() + transaction.getCredit());
              accounts.set(userIndex, userAccount);
            }
          } else if (transaction.getCode().equals("03") || transaction.getCode().equals("04")) {
            // 03 = sell
            // 04 = buy

            if (transaction.getCode().equals("03")) {
              Event event = new Event();
              event.setEventName(transaction.getEventName());
              event.setCount(transaction.countTickets());
              event.setSellerName(transaction.getSellerUsername());
              event.setPrice(transaction.getTicketPrice());

              events.add(event);
            } else if(transaction.getCode().equals("04")) {
              int eventIndex = events.find(transaction.getEventName());
              if (eventIndex == -1) {
                consoleWriter.println("ERROR: Event doesn't exist. " + transactionString);
                return;
              }

              int userIndex = accounts.find(transaction.getSellerUsername());
              if (userIndex == -1) {
                consoleWriter.println("ERROR: User doesn't exist. " + transactionString);
                return;
              }

              Event event = events.get().get(eventIndex);

              int sellerIndex = accounts.find(event.getSeller());
              if (sellerIndex == -1) {
                consoleWriter.println("ERROR: Seller doesn't exist. " + transactionString);
                return;
              }

              UserAccount userAccount = accounts.get(userIndex);
              UserAccount sellerAccount = accounts.get(sellerIndex);
              float amount = event.getPrice() * transaction.countTickets();
              if (userAccount.getCredit() < amount) {
                consoleWriter.println("ERROR: User doesn't have enough funds. " + transactionString);
                return;
              } else if (event.countTickets() <= 0 || event.countTickets() < transaction.countTickets()) {
                consoleWriter.println("ERROR: Event doesn't have enough tickets available. " + transactionString);
                return;
              }

              float balance = userAccount.getCredit();
              balance -= amount;
              userAccount.setCredit(balance);
              accounts.set(userIndex, userAccount);

              balance = sellerAccount.getCredit() + amount;
              sellerAccount.setCredit(balance);
              accounts.set(sellerIndex, sellerAccount);

              int ticketNum = event.countTickets();
              ticketNum -= transaction.countTickets();
              event.setCount(ticketNum);
              events.set(eventIndex, event);
            }
          } else if (transaction.getCode().equals("05")) {
            int userIndex = accounts.find(transaction.getUsername());
            if (userIndex == -1) {
              consoleWriter.println("ERROR: User doesn't exist. " + transactionString);
              return;
            }

            int sellerIndex = accounts.find(transaction.getSellerUsername());
            if (sellerIndex == -1) {
              consoleWriter.println("ERROR: Seller doesn't exist. " + transactionString);
              return;
            }

            UserAccount userAccount = accounts.get(userIndex);
            UserAccount sellerAccount = accounts.get(sellerIndex);

            if (sellerAccount.getCredit() < userAccount.getCredit()) {
              consoleWriter.println("ERROR: Seller does not have enough funds. " + transactionString);
              return;
            }

            float balance = userAccount.getCredit();
            userAccount.setCredit(balance + transaction.getCredit());

            balance = sellerAccount.getCredit();
            sellerAccount.setCredit(balance - transaction.getCredit());

            accounts.set(userIndex, userAccount);
            accounts.set(sellerIndex, sellerAccount);
          }
        }
      } catch(IOException e){
        consoleWriter.println("ERROR: An error occured while processing the transaction file");
      }

    } catch(FileNotFoundException e){
      consoleWriter.println("ERROR: transactions.etf not found");
    }

    //Generate the new user accounts file
    try {
      PrintWriter userAccountWriter = new PrintWriter(userFile, "UTF-8");
      accounts.save(userAccountWriter);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    //Generate the new available tickets file
    try {
      PrintWriter ticketFileReader = new PrintWriter(ticketFile, "UTF-8");
      events.save(ticketFileReader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
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

  public Event_Holder getEvents() {
    return events;
  }
}
