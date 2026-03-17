#!/bin/bash
read -p "Please enter a number: "num

ctr=1 
sum=0


while [ $ctr -le $num ]; do
	let sum=sum+ctr
	echo $ctr
	let ctr++
done
echo "The sum of $num numbers is $sum."

