#!/usr/bin/env bash
source common.sh
${DFS} -rm /user/at15/output/*
${DFS} -rmdir /user/at15/output
${HB} jar ../target/forum-indexer-0.0.1.jar /user/at15/input /user/at15/output