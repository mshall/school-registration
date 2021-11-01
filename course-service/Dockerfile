# define base docker image
FROM openjdk:11
LABEL maintainer="Mohamed S. El-Shall"
ADD target/school-course-service-0.0.1-SNAPSHOT.jar school-course-service.jar
ENTRYPOINT ["java", "-jar", "school-course-service.jar"]