# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Give execute permissions to the Maven wrapper
RUN chmod +x mvnw

# Build the application inside the container
RUN ./mvnw clean package -DskipTests

# Use a smaller runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the generated JAR from the previous build stage
COPY --from=build /app/target/test-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8090
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
