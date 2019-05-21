#!/usr/bin/env bash

 function main {
    NAME=$1
    FS=`findmnt -rno TARGET ${NAME}`

    echo ${FS}
}

main $@