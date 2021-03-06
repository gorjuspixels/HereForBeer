#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
success='\u2713'
fail='\u2717'
NC='\033[0m' # No Color
if [  -f transactions.etf ]; then
    rm transactions.etf
fi
./../../../a.out "1" <input.inp >output.bto
DIFF=$(diff -q -w transactions.etf expected_transactions.etf)
if [ "$DIFF" = "" ];then
	echo -e ${green}$success PASS - DELETE PASS IF USER EXISTS: TRANSACTION OUTPUT ${NC}
else
	echo -e ${red}$fail FAIL - DELETE PASS IF USER EXISTS: TRANSACTION OUTPUT ${NC}
fi
