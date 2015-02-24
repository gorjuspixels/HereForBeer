//add_credit.cpp
#include <iostream>
#include <fstream>
#include "add_credit.h"
using namespace std;

void add_credit(){
	string username;
	string credits;

	//Prompts user to enter the amount of credits to be added
	cout << "Enter the amount of credits to be added" << endl;
	cin >> credits;

	//Prompts user for the username in which credits are being added to
	cout << "Enter the user to recieve the credits" << endl;
	cin >> username;

	//Creates the string to be written to the transaction file
	string daily_transaction = "06 ";
	daily_transaction += username;
	int len = username.length();
	while (len <15){
		daily_transaction += "_";
		len ++;
	}
	daily_transaction +=" AA ";

	len = credits.length();
		while (len < 9){
			daily_transaction +="0";
			len ++;
	}
	daily_transaction +=credits+" ";


	//Writes this transaction to the daily transaction file
	ofstream daily_transaction_file;
  	daily_transaction_file.open ("transactions.etf", ios::app);
  	daily_transaction_file << daily_transaction+"\n";
  	daily_transaction_file.close();
	return;
}