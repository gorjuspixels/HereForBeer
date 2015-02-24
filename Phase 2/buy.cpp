//buy.cpp
#include <iostream>
#include <fstream>
#include "buy.h"
using namespace std;

void buy() {
  string event_title;
  string number_of_tickets;
  string seller;
  string confirmation;

  //Prompts user for event title
  cout << "Enter the event title" << endl;
  cin >> event_title;

  //Prompts user for number of tickets
  cout << "Enter the number of tickets" << endl;
  cin >> number_of_tickets;

  //Prompts user for sellers username
  cout << "Enter the seller's username" << endl;
  cin >> seller;

  //TODO: Output the cost per ticket

  //Asks the user for confirmation
  cout << "Do you accept this transaction?('yes', or 'no')" << endl;
  cin >> confirmation;
  if (confirmation.compare("yes") == 0) {
    //Creates the string for the daily transaction file
    string daily_transaction;
    daily_transaction = "04 ";
    daily_transaction += event_title;
    int len = event_title.length();
    while (len < 25) {
      daily_transaction += "_";
      len ++;
    }
    daily_transaction += " ";
    daily_transaction += seller;
    len = seller.length();
    while (len < 15) {
      daily_transaction += "_";
      len ++;
    }
    daily_transaction += " ";

    len = number_of_tickets.length();
    while (len < 3) {
      daily_transaction += "0";
      len ++;
    }
    daily_transaction += number_of_tickets + " ";

    len = number_of_tickets.length();
    while (len < 9) {
      daily_transaction += "0";
      len ++;
    }
    daily_transaction += number_of_tickets + " ";

    //Writes this transaction to the daily transaction file
    ofstream daily_transaction_file;
    daily_transaction_file.open ("transactions.etf", ios::app);
    daily_transaction_file << daily_transaction + "\n";
    daily_transaction_file.close();

  }
  else {
    return;
  }



  return;
}