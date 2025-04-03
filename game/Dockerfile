FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY build/libs/*.jar app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", "my_postgres:5432", "--", "java", "-jar", "app.jar"]
