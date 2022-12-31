#!/usr/bin/bash
echo "--=[ Stopping Containers ]=--"
docker-compose stop web
cd ./lockedin

echo "--=[ Packaging ]=--"
./mvnw package
cd ..

echo "--=[ Starting Containers ]=--"
docker-compose up -d --build web
docker-compose logs --follow web