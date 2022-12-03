package ies.grupo51.lockedin.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.Message;

@Repository
public interface MessageRepository extends MongoRepository <Message, UUID> {
    
}