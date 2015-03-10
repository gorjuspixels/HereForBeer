//create.cpp
#include <iostream>
#include <fstream>
#include <string>
#include "create.h"
#include "daily_transaction.h"
#include "user_file.h"
using namespace std;
void create() {
  const string ACTION_NAME = "create";

  //Asks user to input a username
  string username = "";
  cout << "Enter a new username: " << endl;
  cin >> username;

  if (username.length() > 15) {
    cout << "ERROR: Username exceeds the limit (15 chars)" << endl;
    return;
  }

  //Asks user for the type of the new user
  string user_type = "";
  cout << "Enter the type of user: Admin: AA, full-standard : FS, buy-standard : BS, or sell-standard: SS " << endl;
  cin >> user_type;

  string credit = "0";

  string data[] = {ACTION_NAME, username, user_type, credit};
  dailyTrans(4, data);
  createUser(4, data);
  return;
}