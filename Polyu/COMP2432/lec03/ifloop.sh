#!/bin/bash
read -p "What was your score?"score
echo You get a schore of $score
if [$score -ge 85 -a $score -le 100 ]; then
	echo You got an A grade!
elif [$score -ge 70 ]; then
	echo You got a B grade!
else 
	echo You better study!!
fi

