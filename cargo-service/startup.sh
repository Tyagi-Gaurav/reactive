#!/bin/sh

export MALLOC_ARENA_MAX=2

set -e

cmd="$1"

until $cmd; do
  >&2 echo "Dependency is unavailable - sleeping"
  sleep 10
done

>&2 echo "Dependency is up - executing command"

java -Dspring.profiles.active=dev -Xms512m -Xmx512m -jar /data/cargo-service.jar
