#!/usr/bin/bash
docker-compose down -v
rm -rf mongodb/data/*
rm -rf mongodb/data/.mongo*
clear