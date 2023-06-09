FROM gradle:latest

WORKDIR /app
COPY . /app
RUN gradle build
ENTRYPOINT ["java", "-jar", "/app/build/libs/bar_user_enable-0.0.1-SNAPSHOT.jar"]