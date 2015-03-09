//add_credit.cpp
#include <iostream>
#include <fstream>
#include "add_credit.h"
#include "daily_transaction.h"
using namespace std;

void add_credit() {
  const string ACTION_NAME = "addcredit";
  string username;
  string credits;

  //Prompts user to enter the amount of credits to be added
  cout << "Enter the amount of credits to be added" << endl;
  cin >> credits;

  //Prompts user for the username in which credits are being added to
  cout << "Enter the user to recieve the credits" << endl;
  cin >> username;

  // TODO: look up user's type
  string data[] = {ACTION_NAME, username, "AA", credits};
  dailyTrans(4, data);
  return;
}