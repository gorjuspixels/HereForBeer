//main.cpp
#include "iostream"
#include <vector>
#include "login.h"
#include "logout.h"
#include "create.h"
#include "delete.h"
#include "sell.h"
#include "buy.h"
#include "refund.h"
#include "add_credit.h"
using namespace std;
int main(int argc, char const *argv[])
{

  string username = "";
  vector<string> user;

  //Prints out the main logo
  cout << "----------------------------------------------------------------------" << endl;
  cout << "|                        Welcome to TicketzPro                        |" << endl;
  cout << "----------------------------------------------------------------------" << endl;

  //Main Program loop
  while (true) {
    cout << "Enter a command: " << endl;

    //gets the user input

    string input;
    cin >> input ;

    //runs methods for each transaction depending on input
    if (input.compare("login") == 0) {
      if (username.length() == 0) {

        user = login();
      } else {
        cout << "ERROR: you are already logged in." << endl;
        continue;
      }

      if (user.size() == 0) {
        cout << "ERROR: No user with specified username exists!" << endl;
        continue;
      }

      username = user[0];
      cout << "Welcome back, " << username << "!" << endl;
    }
    else if (input.compare("logout") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      logout(username);
      username = "";
      cout << "Logout Complete" << endl;
    }
    else if (input.compare("create") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      create();
    }
    else if (input.compare("delete") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      delete_user();
    }
    else if (input.compare("sell") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      sell(username);
    }
    else if (input.compare("buy") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      buy();
    }
    else if (input.compare("refund") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      refund();
    }
    else if (input.compare("add_credit") == 0) {
      if (username.length() == 0) {
        cout << "ERROR: you must be logged in." << endl;
        continue;
      }
      add_credit();
    }
    else if (input.compare("exit") == 0) {
      break;
    }
    else {
      cout << "ERROR: Unknown command" << endl;
    }



  }

  return 0;
}