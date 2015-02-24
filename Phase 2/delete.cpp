//delete.cpp
#include <iostream>
#include <fstream>
#include "delete.h"
#include "daily_transaction.h"

using namespace std;
void delete_user() {
	const string ACTION_NAME = "delete";
	string username;
	//Asks user for the username they wish to remove
	cout << "Enter the username of the user you wish to delete" << endl;
	cin >> username;

	//TODO: lookup user type and credit
	string user_type = "AA";
	string credit = "99999";
	string data[] = {ACTION_NAME, username, user_type, credit};
  dailyTrans(4, data);
	return;
}