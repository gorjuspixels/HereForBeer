#ifndef DATA_STORE_H
#define DATA_STORE_H

#include <string>
using namespace std;

class DataStore {
private:
    string transactionFile = "data/transactions.etf";
    string userFile = "data/userfile.uf";
    string ticketFile = "data/avail_tix.atf";

    DataStore(); // private default constructor

    // C++ 03
    // ========
    // Dont forget to declare these two. You want to make sure they
    // are unacceptable otherwise you may accidentally get copies of
    // your singleton appearing.
    DataStore(DataStore const&); // Don't Implement
    void operator = (DataStore const&); // Don't implement

    // C++ 11
    // =======
    // We can use the better technique of deleting the methods
    // we don't want.
    DataStore(DataStore const&) = delete;
    void operator = (DataStore const&) = delete;

public:
  static DataStore& getInstance(){
      static DataStore instance; // Guaranteed to be destroyed.
                                 // Instantiated on first use.
      return instance;
  }

  void updateStores(string& uFile, string& tixFile, string& transFile);
};

#endif