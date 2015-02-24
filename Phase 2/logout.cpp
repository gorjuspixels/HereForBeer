//logout.cpp
void logout(){
	//Creates the string to be written to the transaction file
	string daily_transaction = "00_000000000000000_00_000000000"


	//Writes this transaction to the daily transaction file
	ofstream daily_transaction_file;
  	daily_transaction_file.open ("transactions.etf", ios::app);
  	daily_transaction_file << daily_transaction+"\n";
  	daily_transaction_file.close();
	return;
}