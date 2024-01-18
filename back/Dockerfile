FROM maven:3-amazoncorretto-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY music_streaming_app/pom.xml ./music_streaming_app/
COPY music_streaming_app/src ./music_streaming_app/src/

COPY security/pom.xml ./security/
COPY security/src ./security/src/

COPY database/pom.xml ./database/
COPY database/src ./database/src/
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-al2-full as final
WORKDIR /app
COPY --from=builder /app/music_streaming_app/target/music_streaming_app-1.0-SNAPSHOT.jar back.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005", "-jar", "back.jar"]



