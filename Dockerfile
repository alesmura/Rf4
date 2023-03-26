# Maven base image
FROM maven:eclipse-temurin AS build

# Copy src
COPY src /home/rf4/src

# Copy pom
COPY pom.xml /home/rf4

# Mvn clean install
RUN mvn -f /home/rf4/pom.xml clean install

# Base image java 17
FROM eclipse-temurin:17.0.6_10-jre-alpine

# Update
RUN apk update

# Copy jar builded
COPY --from=build /home/rf4/target/Rf4-0.0.1.jar /app.jar

# Expose port
EXPOSE $SPRING_PORT

# Launch command
ENTRYPOINT java -jar /app.jar