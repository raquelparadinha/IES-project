package ies.grupo51.lockedin.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.Guard;

@Repository
public interface GuardRepository extends MongoRepository <Guard, UUID> {
    
}
