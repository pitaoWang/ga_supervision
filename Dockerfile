# Version: 0.0.1 wpt
FROM 120.92.105.81:9000/tenx_containers/mvn35-jdk18-sonar3:latest
MAINTAINER pitao_wang "wpt1225@126.com"
RUN mvn clean package
ADD ga-supervision-booter-1.0.1.jar ssm.jar
EXPOSE 18080
ENTRYPOINT ["java", "-jar", "ssm.jar"]
