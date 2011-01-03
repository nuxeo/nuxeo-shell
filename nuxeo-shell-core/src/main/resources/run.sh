#!/bin/sh

SHELL_JAR="%SHELL_JAR%"
JAVA="java"

JAVA -cp ${SHELL_JAR} org.nuxeo.shell.Main $@


