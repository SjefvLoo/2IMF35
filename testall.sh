#!/usr/bin/env sh
for algo in 'trivial' 'improved'; do
    for dir in ./testcases/boolean \
            ./testcases/modal_operators \
            ./testcases/fixpoints_only \
            ./testcases/combined/; do
    	./runall.sh ${algo} ${dir}
    done
done
