/**
 * daily_transaction.cpp
 *
 * Generate daily transaction file for each event.
 * Just execute write() method while passing a string array
 * of transaction parameters in ordered format
 *
 */

#include <string>
#include <fstream>
#include "iostream"
#include "daily_transaction.h"

// Separator (space)
const char SEP = ' ';

// Credit placeholder (zero)
const char ZERO = '0';

/**
 * Returns action number depending on the action name (from the PDF)
 *
 * @param action - action name
 */
string getActionNum(string &action) {
  if (!action.compare("end of session")) {
    return "00";
  } else if (!action.compare("create")) {
    return "01";
  } else if (!action.compare("delete")) {
    return "02";
  } else if (!action.compare("sell")) {
    return "03";
  } else if (!action.compare("buy")) {
    return "04";
  } else if (!action.compare("refund")) {
    return "05";
  } else if (!action.compare("addcredit")) {
    return "06";
  } else {

    cerr << "ERROR: Invalid action name. Please refer to the project decription for action names.\n";
    return "INVALID";
  }
}


/**
 * Formats username. Username is 15 characters. If it is less, add spaces on the right side.
 *
 * @param username - User's username
 * @param usernameLength - username length (for buy & sell it's 13.. otherwise 15)
 */
string formatUsername(string &username, int usernameLength) {
  string spaces = "";
  for (int i = username.size(); i < usernameLength; i++) {
    spaces += SEP;
  }
  return username + spaces;
}

/**
 * Checks if username (buyer's if there are two) is required
 *
 * @param actionNum - Action number
 */
bool usernameRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return false;

    // Buy
  } else if (!actionNum.compare("04")) {
    return false;
  } else {

    return true;
  }
}

/**
 * Checks if seller's username is required
 *
 * @param actionNum - Action number
 */
bool sellerRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return true;

    // Buy
  } else if (!actionNum.compare("04")) {
    return true;

    // Refund
  } else if (!actionNum.compare("05")) {
    return true;
  } else {

    return false;
  }
}

/**
 * Checks if user type is required
 *
 * @param actionNum - Action number
 */
bool userTypeRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return false;

    // Buy
  } else if (!actionNum.compare("04")) {
    return false;

    // Refund
  } else if (!actionNum.compare("05")) {
    return false;
  } else {

    return true;
  }
}


/**
 * Gets user type
 * AA=admin, FS=full-standard, BS=buystandard, SS=sell-standard
 *
 * @param type - User type
 */
string getUserType(string &type) {

  if (type.length() == 2) return type;

  if (!type.compare("admin")) {
    return "AA";
  } else if (!type.compare("full-standard")) {
    return "FS";
  } else if (!type.compare("buy-standard")) {
    return "BS";
  } else if (!type.compare("sell-standard")) {
    return "SS";
  } else {

    cerr << "ERROR: No user type " << type << " found.";
    return "INVALID";
  }
}

/**
 * Checks if availbale credit is required
 *
 * @param actionNum - Action number
 */
bool creditRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return false;

    // Buy
  } else if (!actionNum.compare("04")) {
    return false;
  } else {

    return true;
  }
}

/**
 * Formats credit by adding 0's in front until the string has 9 characters
 *
 * @param: credit - User's available credit
 */
string formatCredit(string &credit) {

  int creditLength = 9;
  string newFormat = "";
  for (int i = credit.size(); i < creditLength; i++) {
    newFormat += ZERO;
  }
  return newFormat + credit;
}

/**
 * Checks if event name is required
 *
 * @param actionNum - Action number
 */
bool eventNameRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return true;

    // Buy
  } else if (!actionNum.compare("04")) {
    return true;
  } else {

    return false;
  }
}

/**
 * Formats event name by adding spaces until it has 19 characters
 *
 * @param: eventName - Event name
 */
string formatEventName(string &eventName) {
  int eventNameLenght = 19;
  string newFormat = "";
  for (int i = eventName.size(); i < eventNameLenght; i++) {
    newFormat += SEP;
  }
  return eventName + newFormat;
}

/**
 * Checks if number of tickets is required
 *
 * @param actionNum - Action number
 */
bool ticketNumRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return true;

    // Buy
  } else if (!actionNum.compare("04")) {
    return true;
  } else {

    return false;
  }
}

/**
 * Formats number of tickets by adding 0's in front until it has 3 characters
 *
 * @param: ticketNum - Number of tickets
 */
string formatTicketNum(string &ticketNum) {
  int ticketNumLength = 3;
  string newFormat = "";
  for (int i = ticketNum.size(); i < ticketNumLength; i++) {
    newFormat += ZERO;
  }
  return newFormat + ticketNum;
}

/**
 * Checks if ticket price is required
 *
 * @param actionNum - Action number
 */
bool ticketPriceRequired(string &actionNum) {

  // Sell
  if (!actionNum.compare("03")) {
    return true;

    // Buy
  } else if (!actionNum.compare("04")) {
    return true;
  } else {

    return false;
  }
}

/**
 * Formats ticket price by adding 0's in front until it has 6 characters
 *
 * @param: ticketPrice - Ticket price
 */
string formatTicketPrice(string &ticketPrice) {
  int ticketNumLength = 6;
  string newFormat = "";
  for (int i = ticketPrice.size(); i < ticketNumLength; i++) {
    newFormat += ZERO;
  }
  return newFormat + ticketPrice;
}


/**
 * Writes to transaction file
 *
 * @param action - action name (from the PDF)
 *
 * args: {"create", "User007", "admin", "999999"} or {"refund", "User007", "UserSeller", "321"};
 */
void dailyTrans(int size, string data[]) {
  string transaction = "";
  string actionNum = getActionNum(data[0]);
  transaction += actionNum;

  // Event name is always at index 0, so start at index 1
  int currentIndex = 1;

  if (eventNameRequired(actionNum)) {
    transaction += SEP + formatEventName(data[currentIndex]);
    currentIndex++;
  }

  if (usernameRequired(actionNum)) {
    transaction += SEP + formatUsername(data[currentIndex], 15);
    currentIndex++;
  }

  if (userTypeRequired(actionNum)) {
    transaction += SEP + getUserType(data[currentIndex]);
    currentIndex++;
  }

  if (sellerRequired(actionNum)) {
    int length = 15;
    if (eventNameRequired(actionNum)) {
      length = 13;
    }
    transaction += SEP + formatUsername(data[currentIndex], length);
    currentIndex++;
  }

  if (ticketNumRequired(actionNum)) {
    transaction += SEP + formatTicketNum(data[currentIndex]);
    currentIndex++;
  }

  if (ticketPriceRequired(actionNum)) {
    transaction += SEP + formatTicketPrice(data[currentIndex]);
    currentIndex++;
  }

  if (creditRequired(actionNum)) {
    transaction += SEP + formatCredit(data[currentIndex]);
    currentIndex++;
  }

  // Writes this transaction to the daily transaction file
  ofstream daily_transaction_file;
  daily_transaction_file.open ("transactions.etf", ios::app);
  daily_transaction_file << transaction << endl;
  daily_transaction_file.close();
  return;
}