package ies.grupo51.lockedin.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.HealthLog;

@Repository
public interface HealthLogRepository extends MongoRepository <HealthLog, Long> {
    public List<HealthLog> findByInmateId(long inmateId);
}
