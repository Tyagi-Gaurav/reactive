FROM openjdk:8u212-jdk-stretch

ADD build/libs/service-discovery-service-DEV.jar /data/service-discovery.jar
COPY ./startup.sh /data/startup.sh

CMD ["/bin/bash", "/data/startup.sh"]