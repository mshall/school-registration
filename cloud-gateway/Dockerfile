# define base docker image
FROM openjdk:11
LABEL maintainer="Mohamed S. El-Shall"
ADD target/school-cloud-gateway-0.0.1-SNAPSHOT.jar school-cloud-gatewayy.jar
ENTRYPOINT ["java", "-jar", "school-cloud-gateway.jar"]