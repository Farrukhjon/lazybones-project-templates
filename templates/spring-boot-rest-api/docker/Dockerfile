FROM farrukhjon/ubuntu-oracle-jdk8:latest

MAINTAINER Farrukhjon SATTOROV firedandy@gmail.com

COPY artifacts/spring-boot-rest-api.jar /opt/services/spring-boot-rest-api.jar

EXPOSE 8080

WORKDIR /opt/services

ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "spring-boot-rest-api.jar"]
