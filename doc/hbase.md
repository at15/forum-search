# HBase 

Row 

> A common row key pattern is a website domain. If your row keys are domains,
  you should probably store them in reverse (org.apache.www, org.apache.mail, org.apache.jira).
  This way, all of the Apache domains are near each other in the table, 
  rather than being spread out based on the first letter of the subdomain.