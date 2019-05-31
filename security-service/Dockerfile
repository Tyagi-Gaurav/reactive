FROM openjdk:8u212-jdk-stretch

ADD build/libs/security-service-DEV.jar /data/security-service.jar

COPY ./startup.sh /data/startup.sh

CMD ["/bin/bash", "/data/startup.sh"]
