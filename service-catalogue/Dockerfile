FROM openjdk:8-jdk-alpine
LABEL maintainer="Gagan Dhand"
EXPOSE 8020
ADD ./target/service-catalogue-0.0.1-SNAPSHOT.jar /
ENTRYPOINT ["java","-jar","service-catalogue-0.0.1-SNAPSHOT.jar"]
