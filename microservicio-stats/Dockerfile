FROM eclipse-temurin:17-jre-alpine
COPY build/libs/microservicio-stats-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]