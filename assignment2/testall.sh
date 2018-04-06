#!/bin/bash
for f in `ls testcases/dining-games/*.gm|grep -v 10|grep -v 11`
do
	name=`basename $f`	
	for strat in "all";
	do
		echo  "Comparing $f with testcases/dining-games/${name%.gm}.maxpg using $strat"
		echo "Running command: ./compare_high_low.sh $f $strat testcases/dining-games/${name%.gm}.maxpg"
		./compare_high_low.sh $f $strat testcases/dining-games/${name%.gm}.maxpg

	done
done
