#!/usr/bin/env bash

cd ../bundle
echo "copy the configs"
cp config/hbase-env.sh hbase-1.1.2/conf
cp config/hbase-site.xml hbase-1.1.2/conf
hbase-1.1.2/bin/start-hbase.sh
jps