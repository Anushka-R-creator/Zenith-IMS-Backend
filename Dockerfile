
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

RUN mvn dependency:go-offline -B
RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/Zenith-IMS-Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

