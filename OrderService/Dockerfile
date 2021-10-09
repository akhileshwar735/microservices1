FROM adoptopenjdk/openjdk11:alpine-slim
WORKDIR /app/akhil/workspace
COPY ./target/order-service-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 5000
ENTRYPOINT java -jar ./app.jar