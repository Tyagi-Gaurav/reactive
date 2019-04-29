FROM java8:0.1
ENV MALLOC_ARENA_MAX=2

ADD build/libs/config-service-DEV.jar /data/config-service.jar

CMD ["java", "-jar", "/data/config-service.jar"]

EXPOSE 8888