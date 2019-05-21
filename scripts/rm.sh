#!/usr/bin/env bash


function main {
    MNT=$1

    CMD=`rm -rf ${MNT}*`

    if [ $? -eq 0 ]; then
        echo 0
    else
        echo ${CMD}
    fi
}

main $@
