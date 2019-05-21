#!/usr/bin/env bash

function main {
    MNT1=$1
    MNT2=$2
    F1=$3
    F2=$4

    mkdir -p ${MNT1}
    mkdir -p ${MNT2}

    touch ${MNT1}/${F1}
    touch ${MNT2}/${F2}
}

main $@