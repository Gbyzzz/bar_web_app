FROM gradle:latest

WORKDIR /app
COPY . /app
RUN gradle build
ENTRYPOINT ["java", "-jar", "/app/build/libs/bar_email-0.0.1-SNAPSHOT.jar"]