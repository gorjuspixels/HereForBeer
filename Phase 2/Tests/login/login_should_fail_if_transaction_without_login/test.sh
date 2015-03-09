#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
NC='\033[0m' # No Color
./../../../a.out <input.inp >output.etf
DIFF=$(diff -q output.etf expected_output.etf)
if [ "$DIFF" = "" ];then
	echo -e ${green} LOGIN FAIL IF TRANSACTION WITHOUT LOGIN: PASS ${NC}
else
	echo -e ${red}LOGIN FAIL IF TRANSACTION WITHOUT LOGIN: FAIL	${NC}
fi
