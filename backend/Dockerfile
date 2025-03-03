# Build stage
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first for better caching
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# Run stage - using a non-Alpine image for better compatibility
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/yearbook-0.0.1-SNAPSHOT.jar app.jar

# Set environment variables - these will be overridden by Coolify
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]