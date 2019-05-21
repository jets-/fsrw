#!/usr/bin/env bash

function main {
    FS=$1
    DEV=$2

    if [ ${FS} = 'ext4' ]; then
        CMD=`mkfs -t ${FS} ${DEV}`
    else
        CMD=`mkfs -t ${FS} -f ${DEV}`
    fi
    echo ${CMD}
}

main $@