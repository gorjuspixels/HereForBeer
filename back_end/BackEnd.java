import java.io.*;
public class BackEnd {
	public static void main(String[] args) {	

		/*TODO:Read the old current user accounts file
		 *     Create a user object for each user
		 *     Add each user to a list, and store in UserAccount_Holder class
		*/


		/*TODO: Read the old available tickets file
		 *      Create an Event class for each event in the file
		 *      Add each event to a list, and store in the Event_Holder class 
		*/


		//Reads the merged daily transaction file
		try{
			FileReader fr = new FileReader("transactions.etf");


			//Reads the transaction file line by line
			try{
				BufferedReader br = new BufferedReader(fr);

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

		


		//TODO: Generate the new user accounts file (UserAccount_Holder.save())


		//TODO: Generate the new available tickets file (Event_Holder.save())

	}
}
