#!/bin/sh
echo '[Git Hook] Executing Spotless check before commit'
# Only compare files that have changed compared to main
#mvnw spotless:check
cd backend/ || exit
./mvnw  spotless:check -DratchetFrom=origin/main
if [ $? -ne 0 ]; then
   echo "Spotless check failed...."
   echo "Running Spotless Formatter to fix the issues....."

   ./mvnw spotless:apply
    if [ $? -ne 0 ]; then
        echo "Spotless Formatter failed...."
        echo "Please fix the issues and commit again...."
        exit 1
    fi

    echo "Code formatted successfully"
    echo "Committing changes....."

fi
#    now format frontend code
#cd ../frontend/ || exit
#npm run format
#if [ $? -ne 0 ]; then
    #echo "Code formatting failed...."
    #echo "Please fix the issues and commit again...."
    #exit 1
#fi
#echo "Committing changes....."
exit $?