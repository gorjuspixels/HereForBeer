/**
 * refund.cpp
 *
 * Refunds an `x` amount from issuer's account into receiver's (buyer's) account
 */

#include "daily_transaction"
void refund() {

  string receiver;
  string issuer;
  string amount;
  string actionName = "refund";

  //Asks the user who issues the refund
  cout << "Enter user's username who is issueing the refund: " <<endl;
  cin >> issuer;

  //Asks the user who receives the refund
  cout << "Enter user's username who is receiving the refund: " <<endl;
  cin >> receiver;

  //Asks for the amount being refunded
  cout << "Amount being refunded: " <<endl;
  cin >> amount;

  string data[] = {actionName, receiver, issuer, "9999999"};
  daily_transaction.write(data);

	return;
}