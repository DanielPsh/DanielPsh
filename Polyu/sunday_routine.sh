#!/bin/bash
# Asking for the schedule until bedtime
echo "What is your schedule for Sunday? (in hours, 0-23)"
read bedtime

current_time=0

echo "Activities until bedtime:"
until [ $current_time -ge $bedtime ]; do
	if [ $current_time -lt 12 ]; then
		echo "At $current_time o'clock, I was having breakfast."
	elif [ $current_time -lt 18 ]; then
		echo "At $current_time o'clock, I went out for playing."
	else
		echo "At $current_time o'clock, I was watching TV."
	fi
	((current_time++))
done

echo "It's bedtime, Mommy! Time to sleep."
