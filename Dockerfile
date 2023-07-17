FROM openjdk:17
ADD target/connextx-java.jar connextx-java.jar
ENTRYPOINT ["java", "-jar","connextx-java.jar"]
EXPOSE 8080