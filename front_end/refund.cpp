#include <string>
#include <iostream>
#include "refund.h"
#include "daily_transaction.h"
using namespace std;

void refund() {

  string receiver;
  string issuer;
  string amount;
  const string ACTION_NAME = "refund";

  //Asks the user who issues the refund
  cout << "Enter user's username who is issueing the refund:" << endl;
  cin >> issuer;

  //Asks the user who receives the refund
  cout << "Enter user's username who is receiving the refund:" << endl;
  cin >> receiver;

  //Asks for the amount being refunded
  cout << "Amount being refunded:" << endl;
  cin >> amount;

  string data[] = {ACTION_NAME, receiver, issuer, "9999999"};
  dailyTrans(4, data);

  return;
}