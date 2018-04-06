#!/bin/bash
for f in `ls testcases/dining-games/*.gm`
do
	name=`basename $f`	
	for strat in "input" "random";
	do
		echo  "Comparing $f with testcases/dining-games/${name%.gm}.maxpg using $strat"
		./compare_high_low.sh $f $strat testcases/dining-games/${name%.gm}.maxpg
	done
done
