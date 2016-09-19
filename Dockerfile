FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Andrei Varabyeu <andrei_varabyeu@epam.com>

RUN apk --no-cache add ttf-droid

VOLUME /tmp
ADD ws-${version}.jar app.jar

RUN sh -c 'touch /app.jar'
EXPOSE 8080

ENTRYPOINT ["java","-Xmx1g","-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]
