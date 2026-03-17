#!/bin/bash

if [ $# -lt 1 ]; then
    echo "Usage: ./expEvalStats.sh <expression1_stored_file> <expression2_stored_file> ..."
    exit 1
fi

total=0
count=0

for file in "$@"; do
    expression=$(<"$file")
    
    result=$(($expression))
    
    count=$((count + 1))
    total=$((total + result))

    echo "# $file Result = $result"
done

if [ $count -gt 0 ]; then
	average=$(( total / count ))
    echo "Average value : $average"
else
    echo "No expressions to evaluate."
fi
