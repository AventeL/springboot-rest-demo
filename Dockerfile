FROM maven:3.9.5-eclipse-temurin-17

WORKDIR /app

COPY . .

COPY ./springboot-rest-demo-config/src/main/resources/springboot-rest-demo.yml .

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar","-Dspring.profiles.active=dev", "-Dspring.config.location=springboot-rest-demo.yml", "springboot-rest-demo-ws/target/springboot-rest-demo-ws-1.0.0-SNAPSHOT.jar"]