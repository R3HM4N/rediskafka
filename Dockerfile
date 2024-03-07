FROM openjdk:19
WORKDIR /app

EXPOSE 8080

COPY target/*.jar uploadImage-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "uploadImage-0.0.1-SNAPSHOT.jar"]