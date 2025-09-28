FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY pom.xml .
COPY src ./src
EXPOSE 8080