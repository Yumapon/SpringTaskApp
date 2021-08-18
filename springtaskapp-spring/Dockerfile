FROM openjdk:11.0.12-jdk

COPY ./target/springtaskapp-spring-0.0.1-SNAPSHOT.jar /root/backend.jar

CMD [ "sh", "-c", "java $JAVA_OPTIONS -jar /root/backend.jar" ]