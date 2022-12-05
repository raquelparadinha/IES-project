package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.MoveSensor;

@Repository
public interface MoveSensorRepository extends MongoRepository <MoveSensor, Long> {
    
}
