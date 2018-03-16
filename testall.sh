#!/usr/bin/env sh
run() {
    echo "=== Run algorithm $1 on LTS $2 testing formula $3 ==="
    java -jar src/out/artifacts/src_jar/src.jar "$@"
}

for algo in 'trivial' 'improved'; do
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
