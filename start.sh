#!/usr/bin/env bash

FILESYSTEMS="xfs ext4 btrfs f2fs"
DEV1=$1
DEV2=$2

for FS in ${FILESYSTEMS}; do
    sudo java -jar target/fsrw*.jar ${FS} ${DEV1} ${DEV2}
done