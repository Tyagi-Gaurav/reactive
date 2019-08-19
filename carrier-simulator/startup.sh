#!/bin/sh

export  MALLOC_ARENA_MAX=2
java -Dspring.profiles.active=local-int -Xms512m -Xmx512m -jar /data/carrier-simulator.jar
