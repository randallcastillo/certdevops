FROM openjdk:8-jre-alpine

COPY target/*.jar /home/certdevops/certdevops.jar

WORKDIR /home/certdevops/

EXPOSE 9997

CMD [ "java", "-jar", "certdevops.jar" ]