/**
 * user_file.cpp
 *
 * Generate user file
 *
 */

#include <string>
#include <fstream>
#include "iostream"
#include "iomanip"
#include <sstream>
#include <vector>
#include "user_file.h"
#include "daily_transaction.h"
using namespace std;

// Separator (space)
const char SEP = ' ';

// Credit placeholder (zero)
const char ZERO = '0';

void createUser(int size, string data[]) {

  if (size < 4) {
    cerr << "ERROR: not enough argument provided when creating a user!";
  }

  // TODO: check if username already exists
  ofstream user_file;
  user_file.open("user_file.uf", ios::app);
  user_file << formatUsername(data[1], 15) << SEP << data[2] << SEP << formatCredit(data[3]) << endl;
  user_file.close();
}

// replaces a character with another character
string replaceinString(string str, string tofind, string toreplace) {
  size_t position = 0;

  for (position = str.find(tofind); position != std::string::npos; position = str.find(tofind,position) ) {
    str.replace(position ,1, toreplace);
  }
  return(str);
}

vector<string> getUser(string &username) {
  ifstream user_file;
  user_file.open("user_file.uf", ios::app);

  vector<string> user;
  string line;
  while (getline(user_file, line)) {

    // find username
    string temp = replaceinString(line.substr(0, 15), " ", "");

    if (temp == username) {
      // the same
      user.push_back(temp);
      user.push_back(replaceinString(line.substr(16, 2), " ", ""));
      user.push_back(replaceinString(line.substr(19, 9), " ", ""));
    }
  }

  user_file.close();

  return user;
}

void deleteUser(string &username) {
  ifstream user_file;
  ofstream tmpFile;
  user_file.open("user_file.uf", ios::app);
  tmpFile.open("tmp.uf", ios::app);

  string line;
  while (getline(user_file, line)) {

    // find username
    string temp = replaceinString(line.substr(0, 15), " ", "");

    if (temp != username) {
      tmpFile << line << endl;
    }
  }

  // remove old file
  user_file.close();
  remove("user_file.uf");

  // rename new file
  tmpFile.close();
  rename("tmp.uf", "user_file.uf");
}

void addCredit(string &username, string &credits) {
  //TODO: implement method
}