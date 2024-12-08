FROM openjdk:17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN java -version
RUN mvn -v
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/iqgame-0.0.1-SNAPSHOT.jar /app/iqgame-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/iqgame-0.0.1-SNAPSHOT.jar"]
