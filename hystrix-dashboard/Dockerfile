# define base docker image
FROM openjdk:11
LABEL maintainer="Mohamed S. El-Shall"
ADD target/school-hystrix-dashboard-0.0.1-SNAPSHOT.jar school-hystrix-dashboar.jar
ENTRYPOINT ["java", "-jar", "school-hystrix-dashboar.jar"]