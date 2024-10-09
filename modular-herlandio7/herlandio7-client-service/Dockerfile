FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY ../pom.xml .
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build ./app/herlandio7-client-service/target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "app.jar"]