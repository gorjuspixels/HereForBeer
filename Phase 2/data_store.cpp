//login.cpp
//Runs the log in transaction, and returns the information for the daily transaction file
#include "data_store.h"
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

// DataStore constructor
DataStore::DataStore() {}

/**
 * Update file stores
 *
 * @param uFile - String: path to user file
 * @param tixFile - String: path to ticket file
 * @param transFile - String: path to transaction file
 */
void DataStore::updateStores(string& uFile, string& tixFile, string& transFile) {
  transactionFile = transFile;
  userFile = uFile;
  ticketFile = tixFile;
}