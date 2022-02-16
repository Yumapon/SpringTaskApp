FROM openjdk:11.0.12-jdk

#グループ、ユーザ作成、必要権限の設定
RUN groupadd backendapp && \
    useradd -g backendapp -m backendapp && \
    mkdir -p /backendapp/logs && \
    touch /backendapp/logs/apigateway.log && \
    chown backendapp /backendapp/logs/apigateway.log

USER backendapp

COPY ./target/springtaskapp-spring-0.0.1-SNAPSHOT.jar /backendapp/backend.jar

CMD [ "sh", "-c", "java $JAVA_OPTIONS -jar /backendapp/backend.jar" ] 