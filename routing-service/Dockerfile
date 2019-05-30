FROM openjdk:8u212-jdk-stretch

ADD build/libs/routing-service-DEV.jar /data/routing-service.jar
COPY ./startup.sh /data/startup.sh

CMD ["/bin/bash", "/data/startup.sh"]
