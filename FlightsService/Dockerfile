FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere die Projektdateien
COPY . .

# Baue das Projekt
RUN mvn clean package

# Schritt 2: Minimaler Laufzeit-Container
FROM openjdk:21-slim

# Kopiere das gebaute JAR aus dem Build-Container
COPY --from=build /app/target/FlightsService-0.0.1-SNAPSHOT.jar app.jar

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "/app.jar"]