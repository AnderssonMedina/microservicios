FROM eclipse-temurin:17-jre-alpine
FROM openjdk:17-jdk-slim
COPY eureka-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]