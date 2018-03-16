#!/usr/bin/env sh
run() {
    echo "=== Run algorithm $1 on LTS $2 testing formula $3 ==="
    o1=`java -jar src/out/artifacts/src_jar/src.jar "$@"`
    echo "$o1"
    echo "Mcrl2 verdict:"
    lps2pbes "`dirname $2`/test.lps" -f $3 > /tmp/c.pbes
    o2=`pbes2bool /tmp/c.pbes`
    echo "$o2"
    echo $o1|grep true &> /dev/null
    v1=$?
    echo $o2|grep true &> /dev/null
    v2=$?
    if [ $v1 -eq $v2 ]
    then
	echo "Correct"
    else
	echo "Incorrect"
    fi
}

for algo in 'naive' 'emersonlei'; do
    for dir in ./testcases/boolean \
            ./testcases/modal_operators \
            ./testcases/fixpoints_only \
            ./testcases/combined/; do
        for file in "${dir}"/form*.mcf; do
            if [ -f "${file}" ]; then
                run "${algo}" "${dir}/test.aut" "${file}"
            fi
        done
    done
done
