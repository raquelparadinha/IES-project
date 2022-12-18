package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Alert;
import ies.grupo51.lockedin.repositories.AlertRepository;

@Service
public class AlertService {
    
    @Autowired
    private AlertRepository repository;
    
    public Alert saveAlert(Alert alert){
        return repository.save(alert);
    }

    public List<Alert> saveAlerts(Set<Alert> alerts) {
        return repository.saveAll(alerts);
    }

    public List<Alert> getAlerts() {
        return repository.findAll();
    }

    public Alert getAlertById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public List<Alert> getAlertByType(String type) {
        return repository.findByType(type);
    }

    public long getNextId() {
        long max_id = 0;
        for (Alert alert : getAlerts()) {
            long id = alert.getId();
            if (id > max_id) {
                max_id = id;
            }
        }
        return max_id+1;
    }
}
