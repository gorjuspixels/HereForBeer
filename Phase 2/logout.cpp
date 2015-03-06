//logout.cpp
//logs the user out, and ends the session
#include "logout.h"
#include "daily_transaction.h"
using namespace std;

void logout(string &username) {
  const string ACTION_NAME = "end of session";

  // TODO: retreive these values
  string credit = "0";
  string user_type = "AA";

  string data[] = {ACTION_NAME, username, user_type, credit};
  dailyTrans(4, data);
  return;
}