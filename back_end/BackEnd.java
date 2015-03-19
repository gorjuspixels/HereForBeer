import java.io.*;
public class BackEnd {
	public static void main(String[] args) {	

		//TODO:Read the old current user accounts file


		//TODO: Read the old available tickets file


		//Reads the merged daily transaction file
		try{
			FileReader fr = new FileReader("transactions.etf");


			//Reads the transaction file line by line
			try{
				BufferedReader br = new BufferedReader(fr);

				//Applies the transactions
				while(br.ready()){
					String transaction = br.readLine();
					/*TODO: Figure out the context of the transaction(First 2 numbers)
					 *      Apply any needed changes to the event and user_account objects
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

		


		//TODO: Generate the new user accounts file


		//TODO: Generate the new available tickets file

	}
}
