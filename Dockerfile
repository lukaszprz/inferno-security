FROM openjdk:8-alpine

COPY * /usr/src/
WORKDIR /usr/src/

CMD java -jar app.jar