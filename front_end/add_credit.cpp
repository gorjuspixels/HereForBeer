//add_credit.cpp
#include <iostream>
#include <fstream>
#include "add_credit.h"
#include "daily_transaction.h"
#include "user_file.h"
using namespace std;

void add_credit(bool testing_env) {
  const string ACTION_NAME = "addcredit";
  string username;
  string credits;

  //Prompts user to enter the amount of credits to be added
  cout << "Enter the amount of credits to be added" << endl;
  cin >> credits;

  //Prompts user for the username in which credits are being added to
  cout << "Enter the user to recieve the credits" << endl;
  cin >> username;

  vector<string> user = getUser(username);
  if (user.size() == 0) {
    cout << "ERROR: User with specified username does not exist" << endl;
    return;
  }

  // TODO: look up user's type
  string data[] = {ACTION_NAME, user[0], user[1], credits};
  dailyTrans(4, data);

  if (!testing_env) {
    addCredit(username, credits);
  }
  return;
}