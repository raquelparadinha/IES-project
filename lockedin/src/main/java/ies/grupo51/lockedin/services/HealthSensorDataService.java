package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.HealthSensorData;
import ies.grupo51.lockedin.repositories.HealthSensorDataRepository;

@Service
public class HealthSensorDataService {
    
    @Autowired
    private HealthSensorDataRepository repository;
    
}
