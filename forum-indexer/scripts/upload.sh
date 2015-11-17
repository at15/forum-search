#!/usr/bin/env bash
source common.sh
echo "upload fixture to hdfs"
${DFS} -put ../fixtures/*.json /user/at15/input2
