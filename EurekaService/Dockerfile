FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /akhil/app/workspace

COPY ./target/eureka-service-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT java -jar ./app.jar