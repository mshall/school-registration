# define base docker image
FROM openjdk:11
LABEL maintainer="Mohamed S. El-Shall"
ADD target/school-service-registry-0.0.1-SNAPSHOT.jar school-service-registry.jar
ENTRYPOINT ["java", "-jar", "school-service-registry.jar"]