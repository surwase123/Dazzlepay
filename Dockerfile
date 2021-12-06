FROM tomcat:9
#Take the war and copy the webapps of tomcat
COPY target/*.war /usr/local/tomcat/webapps/mpls.war
