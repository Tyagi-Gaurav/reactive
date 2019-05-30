#!/bin/sh

export  MALLOC_ARENA_MAX=2
java -Dspring.profiles.active=native -Xms512m -Xmx512m -jar /data/config-service.jar
