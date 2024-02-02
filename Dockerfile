FROM openjdk:17-oracle
WORKDIR /app
COPY build/libs/student-management-0.0.1-SNAPSHOT.jar app.jar
ENV SOURCE_FILE_PATH=./init.txt
CMD ["java", "-jar", "app.jar"]