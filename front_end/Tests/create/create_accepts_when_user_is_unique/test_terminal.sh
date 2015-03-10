#!/bin/bash
red='\033[0;31m'
green='\033[0;32m'
success='\u2713'
fail='\u2717'
NC='\033[0m' # No Color
./../../../a.out "1" <input.inp >output.bto
DIFF=$(diff -q -w output.bto expected_output.bto)
if [ "$DIFF" = "" ];then
	echo -e ${green}$success PASS - CREATE ACCEPTED WHEN USER IS UNIQUE: TERMINAL OUTPUT ${NC}
else
	echo -e ${red}$fail FAIL - CREATE ACCEPTED WHEN USER IS UNIQUE: TERMINAL OUTPUT	${NC}
fi
