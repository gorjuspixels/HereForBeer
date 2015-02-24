//sell.cpp
#include <iostream>
#include <fstream>
#include <string>
#include "sell.h"
using namespace std;

void sell(string username) {
	string event_name;
	string sale_price;
	string tickets_for_sale;

	//Asks the user for the event title
	cout << "Enter the name of the event" <<endl;
	cin >> event_name;

	//Asks the user for the sale price
	cout << "Enter the sale price of the tickets(ex 2.22)" << endl;
	cin >> sale_price;

	//Asks the user for the number of tickets up for sale
	cout << "Enter the number of tickets for sale" << endl;
	cin >> tickets_for_sale;

	//Creates the string for the daily transaction file
	string daily_transaction;
	daily_transaction = "03 ";
	daily_transaction += event_name;
	int len = event_name.length();
	while (len <25){
		daily_transaction += "_";
		len ++;
	}
	daily_transaction +=" ";
	daily_transaction += username;
	len = username.length();
	while (len <15){
		daily_transaction += "_";
		len ++;
	}
	daily_transaction +=" ";

	len = tickets_for_sale.length();
	while (len < 3){
		daily_transaction += "0";
		len ++;
	}
	daily_transaction+= tickets_for_sale + " ";

	len = sale_price.length();
	while (len < 9){
		daily_transaction +="0";
		len ++;
	}
	daily_transaction+= sale_price + " ";

	//Writes this transaction to the daily transaction file
	ofstream daily_transaction_file;
  	daily_transaction_file.open ("transactions.etf", ios::app);
  	daily_transaction_file << daily_transaction+"\n";
  	daily_transaction_file.close();
	return;
}