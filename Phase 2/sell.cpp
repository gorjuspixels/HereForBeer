//sell.cpp
#include <iostream>
#include <fstream>
#include <string>
#include "sell.h"
#include "daily_transaction.h"
using namespace std;

void sell(string username) {

	// ignore 256 chars until new line.. this is so that getline doesnt get skipped
	cin.ignore(256, '\n');
	string event_name;
	string sale_price;
	string tickets_for_sale;
	string actionName = "sell";

	//Asks the user for the event title
	cout << "Enter the name of the event" << endl;

	// Use getline to allow event names with spaces
	getline(cin, event_name);

	//Asks the user for the sale price
	cout << "Enter the sale price of the tickets(ex 2.22)" << endl;
	cin >> sale_price;

	//Asks the user for the number of tickets up for sale
	cout << "Enter the number of tickets for sale" << endl;
	cin >> tickets_for_sale;

	string data[] = {actionName, event_name, username, tickets_for_sale, sale_price};
	dailyTrans(5, data);
	return;
}