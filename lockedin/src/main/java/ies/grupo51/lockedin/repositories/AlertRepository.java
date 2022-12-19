package ies.grupo51.lockedin.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.Alert;

@Repository
public interface AlertRepository extends MongoRepository <Alert, Long>{
    List<Alert> findByType(String type);
}
