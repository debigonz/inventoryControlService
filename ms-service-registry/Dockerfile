FROM eclipse-temurin:21-jre-alpine

EXPOSE 8761

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]