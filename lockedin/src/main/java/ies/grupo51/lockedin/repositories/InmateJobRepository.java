package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.InmateJob;

public interface InmateJobRepository extends MongoRepository <InmateJob, Long> {
    
}
