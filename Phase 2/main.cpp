//main.cpp
#include "iostream"
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

	//Prints out the main logo
	cout << "----------------------------------------------------------------------" << endl;
	cout << "|                        Welcome to TicketzPro                        |"<< endl;
	cout << "----------------------------------------------------------------------" << endl;

	//Main Program loop
	while(true){
		cout << "Enter a command: " << endl;

		//gets the user input

		string input;
		cin >> input ;

		//runs methods for each transaction depending on input
		if(input.compare("login") ==0){
			username = login();
		}
		else if(input.compare("logout") ==0){
			logout();
		}
		else if(input.compare("create") ==0){
			create();
		}
		else if(input.compare("delete") ==0){
			delete_user();
		}
		else if(input.compare("sell") ==0){
			sell(username);
		}
		else if(input.compare("buy") ==0){
			buy();
		}
		else if(input.compare("refund") ==0){
			refund();
		}
		else if(input.compare("add credit") ==0){
			add_credit();
		}
		else if(input.compare("exit") == 0){
			break;
		}
		else{
			cout << "ERROR: Unknown command" << endl;
		}



	}

	return 0;
}