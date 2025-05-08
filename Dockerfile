# Usar a imagem oficial do OpenJDK 17
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR da aplicação (certifique-se de que o JAR foi construído com o Maven)
COPY target/back-service-user-0.0.1-SNAPSHOT.jar back-service-user.jar

# Expor a porta 8081
EXPOSE 8081

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "back-service-user.jar"]
