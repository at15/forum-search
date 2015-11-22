#!/usr/bin/env bash
source common.sh
echo "start uploading fixtures to hdfs"
echo "remove old data"
${DFS} -rm /user/at15/input/*
#${DFS} -put ../../data/*.json /user/at15/input
${DFS} -put ../data/*.json /user/at15/input
echo "done"
