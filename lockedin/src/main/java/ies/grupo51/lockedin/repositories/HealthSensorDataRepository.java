package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.HealthSensorData;

public interface HealthSensorDataRepository extends MongoRepository <HealthSensorData, Long> {
    
}