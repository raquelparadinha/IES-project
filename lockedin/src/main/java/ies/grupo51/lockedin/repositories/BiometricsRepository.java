package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.Biometrics;

public interface BiometricsRepository extends MongoRepository <Biometrics, Long> {
    
}
