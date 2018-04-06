#!/bin/bash
highgame=$3
lowgame=$1
strat=$2

tmphigh=/tmp/high_$RANDOM
tmplow=/tmp/low_$RANDOM
./run $strat $lowgame |sed 's/Even\://'|sed 's/Odd\://'|sed 's/\[//g'|sed 's/\]//g'|sed 's/\ //g' > $tmplow
cat $highgame|pgsolver -global smallprog|grep '{'|sed 's/{//g'|sed 's/\ //g'|sed 's/}//g' > $tmphigh

h1=`cat $tmphigh |head -1|tr ',' '\n'| sort -n|tr '\n' ','`
h2=`cat $tmphigh |tail -1|tr ',' '\n'| sort -n|tr '\n' ','`


l1=`cat $tmplow |head -1|tr ',' '\n'| sort -n|tr '\n' ','`
l2=`cat $tmplow |tail -1|tr ',' '\n'| sort -n|tr '\n' ','`

if [ "$h1" == "$l1" ] && [ "$h2" == "$l2" ];
then
	echo "match"
else
	echo "nee"
fi

