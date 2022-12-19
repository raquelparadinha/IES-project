#! /usr/bin/env bash
echo "--=[ Stopping Containers ]=--"
docker-compose down -v
cd ./lockedin

echo "--=[ Packaging ]=--"
./mvnw package
cd ..

echo "--=[ Starting Containers ]=--"
docker-compose up -d --build mongodb rabbitmq web datagen

echo "--=[ Starting Frontend Development Environment ]=--" 
cd ./frontend
npm start