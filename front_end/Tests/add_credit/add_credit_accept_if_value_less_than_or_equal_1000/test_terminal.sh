#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
success='\u2713'
fail='\u2717'
NC='\033[0m' # No Color
./../../../a.out "1" <input.inp >output.bto
DIFF=$(diff -q -w output.bto expected_output.bto)
if [ "$DIFF" = "" ];then
	echo -e ${green}$success PASS - ADD CREDIT ACCEPT IF VALUE LESS THAN OR EQUAL 1000: TERMINAL OUTPUT ${NC}
else
	echo -e ${red}$fail FAIL - ADD CREDIT ACCEPT IF VALUE LESS THAN OR EQUAL 1000: TERMINAL OUTPUT	${NC}
fi
