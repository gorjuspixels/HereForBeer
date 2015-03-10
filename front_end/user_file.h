//user_file.h
#ifndef USER_FILE_H
#define USER_FILE_H

#include <string>
#include <vector>
using namespace std;

vector<string> getUser(string& username);
void createUser(int size, string data[]);
void deleteUser(string &username);
void addCredit(string &username, string &credits);

#endif