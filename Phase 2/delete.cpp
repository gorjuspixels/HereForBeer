//delete.cpp
#include <iostream>
#include <fstream>
#include "delete.h"
using namespace std;
void delete_user(){
	string username;
	//Asks user for the username they wish to remove
	cout << "Enter the username of the user you wish to delete" << endl;
	cin >> username;

	//Creates the string to be written to the daily transaction file
	string daily_transaction;
	daily_transaction = "02 ";
	daily_transaction += username;
	int len = username.length();
	while (len <15){
		daily_transaction += "_";
		len ++;
	}
	daily_transaction +=" ";
	daily_transaction += "AA ";
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