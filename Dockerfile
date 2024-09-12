# Base image được sử dụng để build image
FROM openjdk:22

# Thông tin tác giả
LABEL authors="deltadc"

# Set working directory trong container
WORKDIR /app

# Copy file JAR được build từ ứng dụng Spring Boot vào working directory trong container
COPY target/quizletclone-0.0.1-SNAPSHOT.jar app.jar

# Expose port của ứng dụng
EXPOSE 8080

# Chỉ định command để chạy ứng dụng khi container khởi chạy
CMD ["java", "-jar", "app.jar"]