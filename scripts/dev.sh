#!/usr/bin/env bash

function main {
    NAME=$1
    CMD=`ls -ld /dev/* /dev/mapper | grep '^b' | grep ${NAME} | awk '{print $NF}'`

    VALUE=0
    if [[ -z ${CMD} ]]; then
        VALUE=1
    fi
    echo ${VALUE}
}

main $@