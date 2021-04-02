FROM openjdk:8-jre-alpine

COPY ./target/alienmw-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
