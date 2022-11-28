package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Inmate;

public interface InmateRepository extends MongoRepository <Inmate, Long> {
    
}
