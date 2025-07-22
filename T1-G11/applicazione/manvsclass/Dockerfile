FROM ubuntu:22.04

RUN apt-get update && apt-get install -y openjdk-17-jdk bash
RUN apt-get install -y openssl libncurses5 libstdc++6 maven git unzip nodejs

COPY target/manvsclass-0.0.1-SNAPSHOT.jar /app/manvsclass.jar

WORKDIR /app

EXPOSE 8080
CMD ["java","-jar", "-Dspring.profiles.active=prod", "manvsclass.jar"]