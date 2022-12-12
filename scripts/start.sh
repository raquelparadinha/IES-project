#!/usr/bin/bash
cd ./lockedin
echo "--=[ Packaging ]=--"
./mvnw package
cd ..
echo "--=[ Starting Containers ]=--"
docker-compose up --build
