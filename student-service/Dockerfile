# define base docker image
FROM openjdk:11
LABEL maintainer="Mohamed S. El-Shall"
ADD target/school-student-service-0.0.1-SNAPSHOT.jar school-student-service.jar
ENTRYPOINT ["java", "-jar", "school-student-service.jar"]