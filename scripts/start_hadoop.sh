#!/usr/bin/env bash

cd ../bundle
echo "copy the configs"
cp config/core-site.xml hadoop-2.7.1/etc/hadoop
cp config/hdfs-site.xml hadoop-2.7.1/etc/hadoop
# for yarn
cp config/mapred-site.xml hadoop-2.7.1/etc/hadoop
cp config/yarn-site.xml hadoop-2.7.1/etc/hadoop
cp config/hadoop-env.sh hadoop-2.7.1/etc/hadoop
echo "start hadoop as daemon"
hadoop-2.7.1/sbin/start-dfs.sh
#hadoop-2.7.1/sbin/start-yarn.sh
