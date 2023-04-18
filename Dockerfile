FROM  amazoncorretto:17-al2023-jdk AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon --info

FROM amazoncorretto:17-al2023-jdk
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY redis.conf .
COPY startdocker.sh .
RUN apt-get update && apt-get install -y redis-server
RUN sed -i 's/bind 127.0.0.1/bind 0.0.0.0/g' /etc/redis/redis.conf
CMD ["redis-server", "/etc/redis/redis.conf"]

FROM amazoncorretto:17-al2023-jdk
RUN apk add --no-cache curl
WORKDIR /app
COPY --from=builder /app/app.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

#openjdk:17-jdk-alpine