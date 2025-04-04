# Use a base image with Java and Maven (or Gradle)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven (or Gradle) build files


# Copy the application source code
COPY target/normalspringboot-0.0.1-SNAPSHOT.jar app.jar

# Build the application


# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose the port your Spring Boot application runs on
EXPOSE 8081

