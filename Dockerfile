FROM openjdk:8-alpine

RUN [“chmod”, “+x”, "/root/app/maven-build.sh”]
COPY * /usr/src/
WORKDIR /usr/src/

CMD java -jar app.jar