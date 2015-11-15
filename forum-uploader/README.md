# Forum uploader 

上传爬取数据到 hdfs

## TODO

- [x] 配置hdfs
- [ ] 上传数据到远程的hdfs (vagrant)

## FAQ

- Q: connection reset by peer
  A: vagrant里的hadoop没有开, `sbin/start-dfs.sh`

- Q: org.apache.hadoop.hdfs.server.namenode.SafeModeException, Name node is in safe mode.
  A: ` bin/hadoop dfsadmin -safemode leave` 需要在虚拟机里执行, 不过**deprecated**了
  
- Q: upload 很长时间然后 File /usr/at15/tmp could only be replicated to 0 nodes instead of minReplication (=1).  There are 1 datanode(s) running and 1 node(s) are excluded in this operation.
  A: 问题应该就是冗余的设置问题, 但是so上的解决方案都没用
  - .create加冗余参数为1, 失败. http://stackoverflow.com/questions/14828662/how-to-set-hadoop-replication-in-java-client-by-class-org-apache-hadoop-conf-con
  - 添加设置, 失败 http://stackoverflow.com/questions/14288453/writing-to-hdfs-from-java-getting-could-only-be-replicated-to-0-nodes-instead