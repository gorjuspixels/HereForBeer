#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
success='\u2713'
fail='\u2717'
NC='\033[0m' # No Color
./../../../a.out "1" <input.inp >output.bto
DIFF=$(diff -q output.bto expected_output.bto)
if [ "$DIFF" = "" ];then
	echo -e ${green}$success PASS - LOGIN FAIL IF TRANSACTION WITHOUT LOGIN: TERMINAL OUTPUT ${NC}
else
	echo -e ${red}$fail FAIL - LOGIN FAIL IF TRANSACTION WITHOUT LOGIN: TERMINAL OUTPUT ${NC}
fi
