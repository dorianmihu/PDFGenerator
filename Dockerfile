FROM java:8
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
EXPOSE 4786
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]