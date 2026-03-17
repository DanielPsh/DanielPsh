#!/bin/bash
read -p "What is your age: " age

if [ $age -ge 18 ]; then
    echo "You are an adult"
else
    echo "You are young"
fi
