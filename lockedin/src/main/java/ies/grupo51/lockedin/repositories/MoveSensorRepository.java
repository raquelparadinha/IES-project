package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.MoveSensor;

public interface MoveSensorRepository extends MongoRepository <MoveSensor, Long> {
    
}
