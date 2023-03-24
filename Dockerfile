FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} assignment.jar
ENTRYPOINT ["java", "-jar", "/assignment.jar"]