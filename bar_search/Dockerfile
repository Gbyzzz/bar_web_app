FROM gradle:latest AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/bar_search-0.0.1-SNAPSHOT.jar /app/bar_search.jar
ENTRYPOINT ["java", "-jar", "/app/bar_search.jar"]