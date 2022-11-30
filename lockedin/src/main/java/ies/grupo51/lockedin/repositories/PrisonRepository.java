package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Prison;

public interface PrisonRepository extends MongoRepository <Prison, Long> {
    
}