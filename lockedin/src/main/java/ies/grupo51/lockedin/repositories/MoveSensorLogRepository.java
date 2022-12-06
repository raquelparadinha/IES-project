package ies.grupo51.lockedin.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.MoveSensorLog;

@Repository
public interface MoveSensorLogRepository extends MongoRepository <MoveSensorLog, Long> {
    List<MoveSensorLog> findByInmateId(long inmateId);
}
