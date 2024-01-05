FROM 1121427423/maven.3.8.6-jdk17.0.5

WORKDIR /app

COPY . .

RUN mvn clean package

CMD ["java", "-jar", "springboot-rest-demo-ws/target/springboot-rest-demo-ws-1.0.0-SNAPSHOT.jar"]