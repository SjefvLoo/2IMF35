#!/bin/bash
for f in *.gm;
do 
	echo $f
	highest=`cat $f|grep -v parity|awk '{print $2}'|sort -n|tail -1`
	minus=`echo $highest + $highest % 2|bc`
	head -1 $f > highgames/$f
	tail -n +2 $f | while read line
	do
		first=`echo $line|awk -F' ' '{ print $1 }'`
		second=`echo $line|awk -F' ' '{ print $2}'`
		rep=`echo $minus - $second|bc`
		last=`echo $line|awk -F' ' '{ print $3" "$4 }'`
		echo $first $rep $last >> highgames/$f
	done   
done
