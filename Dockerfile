FROM  amazoncorretto:17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM amazoncorretto:17
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
COPY redis.conf .
COPY startdocker.sh .
RUN apt-get update && apt-get install -y redis-server
CMD ["./startdocker.sh"]

#openjdk:17-jdk-alpine