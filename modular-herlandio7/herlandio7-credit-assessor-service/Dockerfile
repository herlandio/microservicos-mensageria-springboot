FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG RABBITMQ_HOST=rabbitmq
ARG EUREKA_SERVER=eureka-server
ENTRYPOINT java -jar app.jar