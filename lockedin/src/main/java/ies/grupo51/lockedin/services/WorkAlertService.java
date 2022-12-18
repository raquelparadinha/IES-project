package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.WorkAlert;
import ies.grupo51.lockedin.repositories.WorkAlertRepository;

@Service
public class WorkAlertService {
    
    @Autowired
    private WorkAlertRepository repository;
    
    public WorkAlert saveWorkAlert(WorkAlert workAlert){
        return repository.save(workAlert);
    }

    public List<WorkAlert> saveWorkAlerts(Set<WorkAlert> workAlerts) {
        return repository.saveAll(workAlerts);
    }

    public List<WorkAlert> getWorkAlertAlerts() {
        return repository.findAll();
    }

    public WorkAlert getWorkAlertById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }
}