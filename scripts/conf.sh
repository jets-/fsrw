#!/usr/bin/env bash

function main {
    MNT1=$1
    MNT2=$2

    mkdir -p ${MNT1}
    mkdir -p ${MNT2}
}

main $@