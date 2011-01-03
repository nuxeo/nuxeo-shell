#!/bin/sh

#JAVA_OPT="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"

java ${JAVA_OPT} -cp nuxeo-shell-distribution/target/nuxeo-shell-1.0-SNAPSHOT.jar org.nuxeo.shell.Main http://localhost:8080/nuxeo/site/automation


