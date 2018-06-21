#!/bin/sh
# Script ran inside a docker container intended to build and export the project jar

cd /root/app

# Building
./mvnw clean install
rm target/*.original