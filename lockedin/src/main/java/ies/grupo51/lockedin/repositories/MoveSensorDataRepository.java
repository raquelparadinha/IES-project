package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.MoveSensorData;

public interface MoveSensorDataRepository extends MongoRepository <MoveSensorData, Long> {
    
}
