#!/bin/bash
for f in `ls testcases/dining/*.gm`
do
	name=`basename $f`	
	for strat in "input" "random";
	do
		echo  "Comparing $f with testcases/dining/highgames/$name using $strat"
		./compare_high_low.sh $f $strat testcases/dining/highgames/$name
	done
done
