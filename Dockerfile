# Use JDK 21
FROM eclipse-temurin:21-jdk-alpine

# Create a folder for the app
WORKDIR /app

# Copy the JAR file (created by Maven) into the container
COPY target/*.jar app.jar

# Sentinel runs on 8080
EXPOSE 8080

# Start the engine
ENTRYPOINT ["java", "-jar", "app.jar"]