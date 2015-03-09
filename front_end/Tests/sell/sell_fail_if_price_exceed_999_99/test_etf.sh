#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
NC='\033[0m' # No Color
if [  -f transactions.etf ]; then
    rm transactions.etf
fi
./../../../a.out <input.inp >output.bto
DIFF=$(diff -q -w transactions.etf expected_transactions.etf)
if [ "$DIFF" = "" ];then
	echo -e ${green}SELL FAIL IF PRICE EXCEEDS 999.99: TRANSACTION OUTPUT PASS ${NC}
else
	echo -e ${red}SELL FAIL IF PRICE EXCEEDS 999.99: TRANSACTION OUTPUT FAIL	${NC}
fi
