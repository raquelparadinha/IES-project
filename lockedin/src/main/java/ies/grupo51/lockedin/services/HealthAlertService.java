package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.HealthAlert;
import ies.grupo51.lockedin.repositories.HealthAlertRepository;

@Service
public class HealthAlertService {
    
    @Autowired
    private HealthAlertRepository repository;
    
    public HealthAlert saveHealthAlert(HealthAlert healthAlert){
        return repository.save(healthAlert);
    }

    public List<HealthAlert> saveHealthAlerts(Set<HealthAlert> healthAlerts) {
        return repository.saveAll(healthAlerts);
    }

    public List<HealthAlert> getHealthAlerts() {
        return repository.findAll();
    }

    public HealthAlert getHealthAlertById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }
}