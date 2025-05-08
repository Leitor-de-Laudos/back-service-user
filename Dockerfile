# Usar a imagem oficial do OpenJDK
FROM openjdk:11-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR da aplicação (certifique-se de que o JAR foi construído com o Maven)
COPY target/back-service-user-0.0.1-SNAPSHOT.jar back-service-user.jar

# Expor a porta 8080
EXPOSE 8081

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "back-service-user.jar"]
