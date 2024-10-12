#FROM gradle:latest
#EXPOSE 8080
#WORKDIR /app
#COPY . /app
## fetch dependencies
#RUN chmod +x start.sh && gradle getDeps
#
## script which watches source file changes in background and executes bootRun
#CMD ["sh", "start.sh"]
##ARG JAR_FILE=build/libs/*.jar
##COPY ${JAR_FILE} app.jar
##ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#FROM gradle:latest
#EXPOSE 8080
#WORKDIR /app
#COPY . /app
#RUN gradle build -x test
#ENTRYPOINT ["java", "-jar", "/app/build/libs/bar_backend-0.0.1-SNAPSHOT.jar"]

FROM gradle:latest AS build
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/bar_backend-0.0.1-SNAPSHOT.jar /app/bar_backend.jar
ENTRYPOINT ["java", "-jar", "/app/bar_backend.jar"]