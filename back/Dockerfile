FROM maven:3-amazoncorretto-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-al2-full as final
WORKDIR /app
COPY --from=builder /app/target/music_streaming_app-1.0-SNAPSHOT.jar back.jar

CMD ["java", "-jar", "back.jar"]



