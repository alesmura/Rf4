# Base image
FROM eclipse-temurin:17.0.6_10-jre-alpine

# Update
RUN apk update

# Copy jar
COPY target/Rf4-0.0.1.jar /app.jar

# Expose port
EXPOSE $SPRING_PORT

# Launch command
ENTRYPOINT java -jar /app.jar