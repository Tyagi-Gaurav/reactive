FROM openjdk:8u212-jdk-stretch

ADD build/libs/carrier-simulator.jar /data/carrier-simulator.jar

COPY ./startup.sh /data/startup.sh

CMD ["/bin/bash", "/data/startup.sh"]
