# Version: 0.0.1
FROM maven:3.6-jdk-8
MAINTAINER pitao_wang "wpt1225@126.com"
RUN mvn clean package
ADD ga-supervision-booter-1.0.1.jar ssm.jar
EXPOSE 18080
ENTRYPOINT ["java", "-jar", "ssm.jar"]
