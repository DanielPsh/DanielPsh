#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Usage: ./expVal.sh <expression_stored_file>"
	exit 1
fi

expression=$(<"$1")

validate_expression(){
	local expr="$1"

	if [[ $expr =~ ^[0-9]+(\s*[\+\-\*\/]\s*[0-9]+)*$ ]]; then
		echo "This is wf"
		return 0
	else
		echo "This is not wf"
		return 1
	fi
}

count_symbols() {
    local expr="$1"
    echo "$expr" | tr -cd '0-9+*/-' | wc -c
}

validate_expression "$expression"

symbol_count=$(count_symbols "$expression")
echo "Symbol number: $symbol_count"


