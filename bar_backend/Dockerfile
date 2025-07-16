FROM gradle:latest AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/build/libs/bar_backend-0.0.1-SNAPSHOT.jar /app/bar_backend.jar
ENTRYPOINT ["java", "-jar", "/app/bar_backend.jar"]