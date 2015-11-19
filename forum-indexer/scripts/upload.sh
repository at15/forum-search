#!/usr/bin/env bash
source common.sh
echo "start uploading fixtures to hdfs"
${DFS} -put ../fixtures/*.json /user/at15/input
echo "done"
