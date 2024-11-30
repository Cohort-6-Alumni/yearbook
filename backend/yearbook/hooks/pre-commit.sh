#!/bin/sh
echo '[Git Hook] Executing Spotless check before commit'
# Only compare files that have changed compared to main
mvnw spotless:check #-DratchetFrom=origin/main
exit $?