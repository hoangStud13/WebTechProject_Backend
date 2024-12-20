# Schritt 1: Build-Phase
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Definiere Build-Argumente
ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD

# Kopiere die Projektdateien
COPY pom.xml .
COPY src ./src/

# Baue das Projekt
RUN mvn clean package -Dspring.datasource.url=$SPRING_DATASOURCE_URL \
                      -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME \
                      -Dspring.datasource.password=$SPRING_DATASOURCE_PASSWORD

# Schritt 2: Minimaler Laufzeit-Container
FROM openjdk:21-slim

# Kopiere das gebaute JAR aus dem Build-Container
COPY --from=build /app/target/AccountService-0.0.1-SNAPSHOT.jar app.jar

# Setze Umgebungsvariablen
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "/app.jar"]