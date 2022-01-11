FROM openjdk:12-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} task-service.jar
ENTRYPOINT ["java","-jar","/task-service.jar"]