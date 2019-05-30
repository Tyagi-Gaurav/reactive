FROM openjdk:8u212-jdk-stretch

ADD build/libs/cargo-service-DEV.jar /data/cargo-service.jar
COPY ./startup.sh /data/startup.sh

CMD ["/bin/bash", "/data/startup.sh"]
