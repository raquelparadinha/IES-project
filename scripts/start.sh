#!/usr/bin/bash
docker-compose down -v
cd ./lockedin
echo "--=[ Packaging ]=--"
./mvnw package
cd ..
echo "--=[ Starting Containers ]=--"
docker-compose up
