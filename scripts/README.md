# FAQ

- Q: Mapreduce fail after switched to yarn and got error logs like 

 A: see https://cloudcelebrity.wordpress.com/2014/01/31/yarn-job-problem-application-application_-failed-1-times-due-to-am-container-for-xx-exited-with-exitcode-127/
    need to config bin/java .... but wtf why it can't use JAVA_HOME ? 
   
   Must link bin/java ... otherwise bin/hadoop -mkdir input also wont work