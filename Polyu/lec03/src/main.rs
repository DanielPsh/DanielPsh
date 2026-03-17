#!/bin/bash
# Asking about the time of day
echo "What time is it(in hours, 0-23)?"
read time

# Determine the activity based on the time of day
if [$time -lt 12]; then
    echo "Good morning, Mom! I'll have breakfast at $time o'clock,"
elif [$time -lt 18]; then
    echo "Good afternoon, Mom I'll go out to play at $time o'clock."
else
    echo "Good evening, Mom! I'll spend time watching TV at $time o'clock."
fi
