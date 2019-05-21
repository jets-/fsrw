#!/usr/bin/env bash

function main {
    DEV=$1
    CMD=`findmnt -rno SOURCE ${DEV}`

    VALUE=0
    if [[ -z ${CMD} ]]; then
        VALUE=1
    fi
    echo ${VALUE}
}

main $@