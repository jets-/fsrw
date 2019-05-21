#!/usr/bin/env bash

function main {
    DEV=$1
    MNT=$2

    mount ${DEV} ${MNT}
}

main $@
