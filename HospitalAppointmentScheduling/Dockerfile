FROM openjdk:17-slim

WORKDIR /app

# Copy the JAR file into the container (assuming there's only one JAR in the context)
COPY target/*.jar app.jar

EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]