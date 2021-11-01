# define base docker image
FROM openjdk:11
LABEL maintainer="Mohamed S. El-Shall"
ADD target/school-cloud-config-server-0.0.1-SNAPSHOT.jar school-cloud-config-server.jar
ENTRYPOINT ["java", "-jar", "school-cloud-config-server.jar"]