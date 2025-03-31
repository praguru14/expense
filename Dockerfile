# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and set execution permissions
COPY mvnw .
RUN chmod +x mvnw

# Copy the project files
COPY . .

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Copy the generated JAR file to the container
COPY target/test-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8090
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
