FROM amazoncorretto:21-al2-full

RUN mvn compile && mvn clean package

ADD /target/music_streaming_app-1.0-SNAPSHOT.jar back.jar

ENTRYPOINT ["java", "-jar", "back.jar"]