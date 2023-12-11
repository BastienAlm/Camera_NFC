FROM openjdk:8-jdk-alpine

COPY target/gotham-0.0.1-SNAPSHOT.jar gotham-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/gotham-0.0.1-SNAPSHOT.jar"]