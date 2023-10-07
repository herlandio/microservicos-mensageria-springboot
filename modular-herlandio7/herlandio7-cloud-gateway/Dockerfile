FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG EUREKA_SERVER=eureka-server
ARG KEYCLOAK_SERVER=localhost
ARG KEYCLOAK_PORT=8081
ENTRYPOINT java -jar app.jar