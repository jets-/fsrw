#!/usr/bin/env bash

function main {
	DEV=$1
	umount -l ${DEV}
}

main $@