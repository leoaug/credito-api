# ===============================
# STAGE 1 - Build
# ===============================
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia apenas o necessário para cache de dependências
COPY pom.xml .
RUN ./mvnw dependency:go-offline || true

# Copia o código e compila
COPY src ./src
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw clean package -DskipTests

# ===============================
# STAGE 2 - Runtime
# ===============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia apenas o jar final
COPY --from=build /app/target/credito-api-*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Variáveis para produção
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
