//create.cpp
#include <iostream>
#include <fstream>
#include <string>
#include "create.h"
using namespace std;
void create(){

	//Asks user to input a username
	string username = "";
	cout << "Enter a new username: " << endl;
	cin >> username;

	//Asks user for the type of the new user
	string user_type = "";
	cout << "Enter the type of user: Admin: AA, full-standard : FS, buy-standard : BS, or sell-standard: SS " <<endl;
	cin >> user_type;

	//Creates the string to be written to the transaction file
	string daily_transaction = "01 ";
	daily_transaction += username;
	int len = username.length();
	while (len <15){
		daily_transaction += "_";
		len ++;
	}
	daily_transaction +=" ";

	//Adds the underscores as the credit placeholder
	daily_transaction += user_type + " ";
	for(int i = 0; i < 9; i++ ){
		daily_transaction +="_";
	}


	//Writes this transaction to the daily transaction file
	ofstream daily_transaction_file;
  	daily_transaction_file.open ("transactions.etf", ios::app);
  	daily_transaction_file << daily_transaction+"\n";
  	daily_transaction_file.close();


	return;
}