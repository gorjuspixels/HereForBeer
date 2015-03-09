#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
NC='\033[0m' # No Color
./../../../a.out <input.inp >output.etf
DIFF=$(diff -q -w output.etf expected_output.etf)
if [ "$DIFF" = "" ];then
	echo -e ${green}LOGIN SHOULD FAIL IF ALREADY LOGGED IN: PASS ${NC}
else
	echo -e ${red}LOGIN SHOULD FAIL IF ALREADY LOGGED IN: FAIL	${NC}
fi
