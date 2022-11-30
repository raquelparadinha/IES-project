package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Workstation;

public interface InmateJobRepository extends MongoRepository <Workstation, Long> {
    
}
