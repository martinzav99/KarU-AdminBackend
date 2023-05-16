FROM amazoncorretto:8-alpine-jdk
MAINTAINER martin
COPY target/gadmin-backend-0.0.1-SNAPSHOT.jar spring-app.jar
ENTRYPOINT ["java","-jar","/spring-app.jar"]