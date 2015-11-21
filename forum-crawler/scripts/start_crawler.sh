#!/usr/bin/env bash
cd ..
# craw hot
#java -jar target/forum-crawler-0.0.1.jar "http://www.1point3acres.com/bbs/" "http://www.1point3acres.com/bbs/forum.php?mod=guide&view=hot" "/home/at15/workspace/data/craw/1m3" 10
# craw everything
java -jar target/forum-crawler-0.0.1.jar "http://www.1point3acres.com/bbs/" "http://www.1point3acres.com/bbs/" "/home/at15/workspace/data/craw/1m3" 10