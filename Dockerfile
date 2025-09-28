# --- Stage 1: The Builder ---
# Use an official Maven image to get a full build environment
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml AND the source code all at once.
# This is a more robust approach to ensure everything is in place before the build.
COPY . .

# Now, run the build command. This will download dependencies and compile the app.
RUN mvn clean install -DskipTests


# --- Stage 2: The Runner ---
# Use a minimal Java runtime image for a small and secure final image
FROM eclipse-temurin:17-jre-jammy

# Set the working directory
WORKDIR /app

# Copy ONLY the final JAR file from the builder stage
COPY --from=builder /app/target/Zenith-IMS-Backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]