package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Guard;

public interface GuardRepository extends MongoRepository <Guard, Long> {
    
}
