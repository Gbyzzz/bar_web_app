FROM gradle:latest AS build
WORKDIR /app
COPY . /app
ARG KEYSTORE_PASSWORD
RUN mkdir -p src/main/resources/keystore && \
        keytool -genkeypair \
        -alias mykey \
        -keyalg RSA \
        -keystore src/main/resources/keystore/keystore.p12 \
        -storetype PKCS12 \
        -storepass ${KEYSTORE_PASSWORD} \
        -keypass ${KEYSTORE_PASSWORD} \
        -dname "CN=example.com, OU=IT, O=Example Corp, L=City, ST=State, C=US" \
        -validity 3650
RUN gradle build -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/bar_search-0.0.1-SNAPSHOT.jar /app/bar_search.jar
ENTRYPOINT ["java", "-jar", "/app/bar_search.jar"]