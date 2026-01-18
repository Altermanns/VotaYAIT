FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /build

# Copy only maven files first to leverage layer caching
COPY pom.xml .
COPY src ./src

RUN mvn -B package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
