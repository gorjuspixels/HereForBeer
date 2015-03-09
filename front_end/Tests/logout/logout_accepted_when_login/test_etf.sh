#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
NC='\033[0m' # No Color
rm transactions.etf
./../../../a.out <input.inp >output.bto
DIFF=$(diff -q -w transactions.etf expected_transactions.etf)
if [ "$DIFF" = "" ];then
	echo -e ${green}LOGOUT ACCEPTED WHEN LOGGED IN: TRANSACTION OUTPUT PASS ${NC}
else
	echo -e ${red}LOGOUT ACCEPTED WHEN LOGGED IN: TRANSACTION OUTPUT FAIL	${NC}
fi
