FROM openjdk:17
WORKDIR /quiz
COPY build/libs/quiz-app-0.0.1-SNAPSHOT.jar /quiz/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]