//delete.cpp
#include <iostream>
#include <fstream>
#include <vector>
#include "delete.h"
#include "user_file.h"
#include "daily_transaction.h"

using namespace std;
void delete_user(vector<string> currentUser, bool testing_env) {
  const string ACTION_NAME = "delete";

  if (currentUser[1] != "AA") {
    cout << "ERROR: Access denied. You must be administrator to complete this action" << endl;
    return;
  }

  string username;
  //Asks user for the username they wish to remove
  cout << "Enter the username of the user you wish to delete" << endl;
  cin >> username;

  vector<string> user = getUser(username);
  if (user.size() == 0) {
    cout << "ERROR: Cannot delete a user that does not exist" << endl;
    return;
  } else if (username == currentUser[0]) {
    cout << "ERROR: Cannot delete current user" << endl;
    return;
  }

  string data[] = {ACTION_NAME, user[0], user[1], user[2]};
  dailyTrans(4, data);

  if (!testing_env) {
    deleteUser(username);
  }

  cout << "SUCCESS: User Deleted" << endl;
  return;
}