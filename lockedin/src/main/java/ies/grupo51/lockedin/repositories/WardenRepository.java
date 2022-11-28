package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Warden;

public interface WardenRepository extends MongoRepository <Warden, Long> {
    
}
