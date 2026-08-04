# ==========================
# Etapa 1 - Build
# ==========================
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia apenas os arquivos necessários para baixar as dependências
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copia o restante do projeto
COPY src src

# Gera o JAR
RUN ./mvnw clean package -DskipTests

# ==========================
# Etapa 2 - Runtime
# ==========================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]