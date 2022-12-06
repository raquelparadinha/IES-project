package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.WorkLog;

@Repository
public interface WorkLogRepository extends MongoRepository <WorkLog, Long> {
    
}
