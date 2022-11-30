package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Healthcheck;

public interface HealthSensorDataRepository extends MongoRepository <Healthcheck, Long> {
    
}