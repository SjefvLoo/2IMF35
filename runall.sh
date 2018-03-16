#!/usr/bin/env bash
rootdir=$2
algo=$1
domcrl=$3

run() {
    echo "=== Run algorithm $1 on LTS $2 testing formula $3 ==="
    time o1=`java -jar src/out/artifacts/src_jar/src.jar "$@"`
    echo "$o1"
    if [ "$domcrl" != "--nomcrl" ];
    then
	    echo "Mcrl2 verdict:"
	    lps2pbes "`echo $2|sed s/aut$/lps/`" -f $3 > /tmp/c.pbes
	    o2=`pbes2bool /tmp/c.pbes`
	    echo "$o2"
	    { echo "$o1"|grep "Verdict: true"; } &> /dev/null
	    v1=$?
	    { echo "$o2"|grep "true"; } &> /dev/null
	    v2=$?
	    if [ $v1 -eq $v2 ]
	    then
		echo "Correct"
	    else
		echo "Incorrect"
	    fi
    fi
}

for aut in "${rootdir}"/*.aut; do
	for mcf in "${rootdir}"/*.mcf; do
		run "${algo}" "${aut}" "${mcf}"
	done
done

#for algo in 'trivial' 'improved'; do
#    for dir in ./testcases/boolean \
#            ./testcases/modal_operators \
#            ./testcases/fixpoints_only \
#            ./testcases/combined/; do
#        for file in "${dir}"/form*.mcf; do
#            if [ -f "${file}" ]; then
#                run "${algo}" "${dir}/test.aut" "${file}"
#            fi
#        done
#    done
#done
