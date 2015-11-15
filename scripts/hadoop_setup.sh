#!/usr/bin/env bash

cd ../bundle
wget http://mirrors.cnnic.cn/apache/hadoop/common/stable/hadoop-2.7.1.tar.gz
tar -zxf hadoop-2.7.1.tar.gz
# TODO: handle ssh
hadoop-2.7.1/bin/hdfs namenode -format