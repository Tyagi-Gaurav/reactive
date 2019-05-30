FROM openjdk:8u212-jdk-stretch

ADD build/libs/config-service-DEV.jar /data/config-service.jar

COPY ./config /Users/gauravt/workspace/reactive/configserver/
COPY ./startup.sh /data/startup.sh

CMD ["/bin/bash", "/data/startup.sh"]
