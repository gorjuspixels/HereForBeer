//login.cpp
//Runs the log in transaction, and returns the information for the daily transaction file
#include "login.h"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "user_file.h"
using namespace std;

vector<string> login() {
  string username = "";
  //TODO: Read in current user accounts file

  //Prompts user to enter their username
  cout << "Please enter your username" << endl;
  cin >> username;

  //TODO: Check that username exsists within the current user accounts file

  //TODO: Read in the available tickets file
  vector<string> user = getUser(username);
  return user;
}