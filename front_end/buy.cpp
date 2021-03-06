//buy.cpp
#include <iostream>
#include <fstream>
#include <vector>
#include "buy.h"
#include "daily_transaction.h"
#include "user_file.h"
using namespace std;

void buy() {
  const string ACTION_NAME = "buy";
  string event_name;
  string tickets_for_sale;
  string seller;
  string confirmation;
  string accountType;

  //Prompts user for event title
  cout << "Enter the event title" << endl;
  cin >> event_name;

  //Prompts user for number of tickets
  cout << "Enter the number of tickets" << endl;
  cin >> tickets_for_sale;

  //Prompts user for sellers username
  cout << "Enter the seller's username" << endl;
  cin >> seller;

  vector<string> sellerAccount = getUser(seller);

  bool permissionGranted = true;
  if (!sellerAccount[1].compare("SS")) {
    permissionGranted = false;
  }

  if (!permissionGranted) {
    cout << "ERROR: Permission denied. Your account type is not sufficient to buy." << endl;
    return;
  }

  //Asks the user for confirmation
  cout << "Do you accept this transaction?('yes', or 'no')" << endl;
  cin >> confirmation;

  if (confirmation.compare("yes") == 0) {

    //TODO: Output the cost per ticket

    // record transaction
    string sale_price = "100";
    string data[] = {ACTION_NAME, event_name, seller, tickets_for_sale, sale_price};
    dailyTrans(5, data);
  }

  return;
}