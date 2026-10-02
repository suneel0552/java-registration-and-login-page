FROM eclipse-temurin:11-jre-alpine

WORKDIR /app
COPY target/*.jar application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
