#!/bin/bash
function run {
        echo "=== Run $1 with $2 ==="
        java -jar src/out/artifacts/src_jar/src.jar $1 $2
}
    

for i in `seq 1 9`
do
	run testcases/boolean/test.aut testcases/boolean/form${i}.mcf
done

for i in `seq 1 5`
do
        run testcases/modal_operators/test.aut testcases/modal_operators/form${i}.mcf
done


for i in `seq 1 5`
do
        run testcases/fixpoints_only/test.aut testcases/fixpoints_only/form${i}.mcf
done

for i in `seq 1 5`
do
        run testcases/combined/test.aut testcases/combined/form${i}.mcf
done

