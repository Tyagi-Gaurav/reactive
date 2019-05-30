#!/bin/sh
export MALLOC_ARENA_MAX=2

java -Dspring.profiles.active=dev -Xms512m -Xmx512m -jar /data/service-discovery.jar
