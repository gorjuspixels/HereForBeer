import java.io.*;
public class BackEnd {
  private static final String USER_ACCOUNTS = "UserAccounts.txt";
  private static final String AVAIL_TICKETS = "AvailableTickets.txt";
  private static FileReader accountFileReader;
  private static FileReader ticketFileReader;

  public static void main(String[] args) {

		//Creates holder classes for the events, and accounts
		UserAccount_Holder accounts = new UserAccount_Holder();
		Event_Holder events = new Event_Holder();

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
			FileReader transaction_fr = new FileReader("transactions.etf");


			//Reads the transaction file line by line
			try{
				BufferedReader br = new BufferedReader(transaction_fr);

				//Applies the transactions
				while(br.ready()){
					String transaction = br.readLine();
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
		accounts.save();

		//Generate the new available tickets file
		events.save();
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
}
