FROM openjdk:8-jre
MAINTAINER Vhans <2873585297@qq.com>


RUN mkdir /app
COPY config-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8777