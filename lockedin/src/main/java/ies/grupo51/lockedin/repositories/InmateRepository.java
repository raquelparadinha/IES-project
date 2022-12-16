package ies.grupo51.lockedin.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.Inmate;

@Repository
public interface InmateRepository extends MongoRepository <Inmate, Long> {
    List<Inmate> findByAreaId(long areaId);
}
