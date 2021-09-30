FROM openjdk:11.0.12-jdk

# Dockerコンテナに変数を渡す挙動をDockerCompose側に移動
#ARG url
#ARG username
#ARG password
#ARG driver

#ENV SPRING_DATASOURCE_URL=$url
#ENV SPRING_DATASOURCE_USERNAME=$username
#ENV SPRING_DATASOURCE_PASSWORD=$password
#ENV SPRING_DATASOURCE_DRIVERCLASSNAME=$driver

COPY ./target/springtaskapp-spring-0.0.1-SNAPSHOT.jar /root/backend.jar

CMD [ "sh", "-c", "java $JAVA_OPTIONS -jar /root/backend.jar" ]