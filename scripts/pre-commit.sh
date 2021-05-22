#!/bin/sh; C:/Program\ Files/Git/bin/sh.exe
echo "Running spotless"
./gradlew spotlessApply

status=$?

# return 1 exit code if running checks fails
[ $status -ne 0 ] && exit 1
exit 0