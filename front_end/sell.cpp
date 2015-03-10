//sell.cpp
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include "sell.h"
#include "daily_transaction.h"
using namespace std;

void sell(vector<string> currentUser) {

  if (currentUser[1] == "BS") {
    cout << "ERROR: Access denied. Your account type may not sell" << endl;
    return;
  }

  // ignore 256 chars until new line.. this is so that getline doesnt get skipped
  cin.ignore(256, '\n');
  string event_name;
  string sale_price;
  string tickets_for_sale;
  const string ACTION_NAME = "sell";

  //Asks the user for the event title
  cout << "Enter the name of the event" << endl;

  // Use getline to allow event names with spaces
  getline(cin, event_name);

  if (event_name.length() > 25) {
    cout << "ERROR: Event name must be 25 or less characters" << endl;
    return;
  }

  //Asks the user for the sale price
  cout << "Enter the sale price of the tickets(ex 2.22)" << endl;
  cin >> sale_price;

  //Asks the user for the number of tickets up for sale
  cout << "Enter the number of tickets for sale" << endl;
  cin >> tickets_for_sale;

  if (atoi(tickets_for_sale.c_str()) > 100) {
    cout << "ERROR: Number of tickets may not exceed 100" << endl;
    return;
  }

  string data[] = {ACTION_NAME, event_name, currentUser[0], tickets_for_sale, sale_price};
  dailyTrans(5, data);
  return;
}