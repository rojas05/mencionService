FROM openjdk:17
WORKDIR /app
COPY target/mencionService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE ${PORT}
CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]