FROM openjdk:8-jdk-alpine
EXPOSE 8090
RUN mkdir -p /images
COPY src/main/java/com/picposter/images/* /home/images/
ADD target/picposter.jar picposter.jar
ENTRYPOINT ["java", "-jar", "/picposter.jar"]
