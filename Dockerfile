FROM openjdk:17
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE ${PORT}
CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]