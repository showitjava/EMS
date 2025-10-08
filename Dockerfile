FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/*.jar Ems-app.jar

ENTRYPOINT [ "java","-jar","/app/Ems-app.jar" ]

