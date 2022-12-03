package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.MoveSensorData;

@Repository
public interface MoveSensorDataRepository extends MongoRepository <MoveSensorData, Long> {
    
}
