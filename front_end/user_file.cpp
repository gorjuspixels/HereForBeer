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
#include "user_file.h"
#include "daily_transaction.h"
using namespace std;

// Separator (space)
const char SEP = ' ';

// Credit placeholder (zero)
const char ZERO = '0';


void write(string &data) {
  // Writes this transaction to the daily transaction file
  ofstream user_file;
  user_file.open ("user_file.uf", ios::app);
  user_file << data << endl;
  user_file.close();
}


void createUser(int size, string data[]) {

  if (size < 4) {
    cerr << "ERROR: not enough argument provided when creating a user!";
  }

  // TODO: check if username already exists
  ofstream user_file;
  user_file.open ("user_file.uf", ios::app);
  user_file << formatUsername(data[1], 15) << SEP << data[2] << SEP << formatCredit(data[3]) << endl;
  user_file.close();
}