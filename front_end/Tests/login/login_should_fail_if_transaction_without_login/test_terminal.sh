#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
NC='\033[0m' # No Color
./../../../a.out <input.inp >output.bto
DIFF=$(diff -q output.bto expected_output.bto)
if [ "$DIFF" = "" ];then
	echo -e ${green}LOGIN FAIL IF TRANSACTION WITHOUT LOGIN: TERMINAL OUTPUT PASS ${NC}
else
	echo -e ${red}LOGIN FAIL IF TRANSACTION WITHOUT LOGIN: TERMINAL OUTPUT FAIL	${NC}
fi
