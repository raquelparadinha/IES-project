package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.HealthSensor;

public interface HealthSensorRepository extends MongoRepository <HealthSensor, Long> {
    
}
