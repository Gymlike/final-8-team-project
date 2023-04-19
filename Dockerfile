FROM amazoncorretto:17-al2023-jdk
ARG JAR_FILE=build/libs/*.jar
COPY $JAR_FILE app.jar
ENTRYPOINT  ["java", "-jar", "/app/Final8TeamProjectApplication.jar"]