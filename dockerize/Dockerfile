
#Dockerfile

FROM openjdk:20
LABEL maintainer="My todolist for Spring Boot"
ARG JAR_FILE_PATH=ToDo-App-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE_PATH} todo.jar
ENTRYPOINT ["java","-jar","/todo.jar"]
EXPOSE 2222
