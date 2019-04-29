FROM openjdk:11.0.3-jdk-stretch
ENV MALLOC_ARENA_MAX=2

ADD build/libs/config-service-DEV.jar /data/config-service.jar

COPY ./config /Users/gauravt/workspace/reactive/configserver/

CMD ["java", "-jar", "/data/config-service.jar"]

EXPOSE 8888