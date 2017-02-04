#!/bin/sh
rm -f tpid
nohup java -jar /site/code/rank_site/site-trunk.jar --spring.profiles.active=prod > /dev/null 2>&1 &
echo $! > tpid