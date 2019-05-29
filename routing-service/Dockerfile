FROM openjdk:8u212-jdk-stretch
ENV MALLOC_ARENA_MAX=2

ADD build/libs/routing-service-DEV.jar /data/routing-service.jar

CMD ["java", "-jar", "/data/routing-service.jar"]