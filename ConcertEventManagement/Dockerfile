# Select the base image to use
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the host machine to the container's working directory
COPY target/ConcertEventManagement-0.0.1-SNAPSHOT.jar app.jar

# Specifies the command to be executed when the container is running
CMD ["java", \
     "-Dcom.sun.management.jmxremote", \
     "-Dcom.sun.management.jmxremote.port=9010", \
     "-Dcom.sun.management.jmxremote.rmi.port=9010", \
     "-Dcom.sun.management.jmxremote.ssl=false", \
     "-Dcom.sun.management.jmxremote.authenticate=false", \
     "-Djava.rmi.server.hostname=118.223.57.205", \
     "-jar", "app.jar"]

# Specifies the port to be opened inside the container
EXPOSE 8080
