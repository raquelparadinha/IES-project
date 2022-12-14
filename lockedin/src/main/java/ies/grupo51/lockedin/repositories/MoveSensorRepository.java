package ies.grupo51.lockedin.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.MoveSensor;

@Repository
public interface MoveSensorRepository extends MongoRepository <MoveSensor, Long> {
    public List<MoveSensor> findByEntryAreaId(long entryAreaId);
    public List<MoveSensor> findByExitAreaId(long exitAreaId);
}
