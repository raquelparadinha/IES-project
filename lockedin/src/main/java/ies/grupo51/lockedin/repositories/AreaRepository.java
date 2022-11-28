package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Area;

public interface AreaRepository extends MongoRepository <Area, Long> {
    
}
