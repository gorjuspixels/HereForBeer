#include <string>
#include <iostream>
#include "refund.h"
#include "user_file.h"
#include "daily_transaction.h"
using namespace std;

void refund() {

  string receiverUsername;
  string issuerUsername;
  vector<string> receiver;
  vector<string> issuer;
  string amount;
  const string ACTION_NAME = "refund";

  //Asks the user who issues the refund
  cout << "Enter user's username who is issueing the refund:" << endl;
  cin >> issuerUsername;

  issuer = getUser(issuerUsername);
  if (issuer.size() == 0) {
    cout << "ERROR: User with specified username doesn't exist" << endl;
    return;
  }

  //Asks the user who receives the refund
  cout << "Enter user's username who is receiving the refund:" << endl;
  cin >> receiverUsername;

  receiver = getUser(receiverUsername);
  if (receiver.size() == 0) {
    cout << "ERROR: User with specified username doesn't exist" << endl;
    return;
  }

  //Asks for the amount being refunded
  cout << "Amount being refunded:" << endl;
  cin >> amount;

  string data[] = {ACTION_NAME, receiverUsername, issuerUsername, amount};
  dailyTrans(4, data);

  // TODO: make changes to user file

  return;
}