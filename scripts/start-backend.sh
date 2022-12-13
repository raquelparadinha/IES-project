#!/usr/bin/bash
cd lockedin/
./mvnw package
cd ..
docker-compose up --build web