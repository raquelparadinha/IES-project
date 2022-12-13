package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.EstiaAlert;
import ies.grupo51.lockedin.repositories.EstiaAlertRepository;

@Service
public class EstiaAlertService {
    
    @Autowired
    private EstiaAlertRepository repository;
    
    public EstiaAlert saveAlert(EstiaAlert estiaAlert){
        return repository.save(estiaAlert);
    }

    public List<EstiaAlert> saveEstiaAlerts(Set<EstiaAlert> estiaAlerts) {
        return repository.saveAll(estiaAlerts);
    }

    public List<EstiaAlert> getEstiaAlertAlerts() {
        return repository.findAll();
    }

    public EstiaAlert getEstiaAlertById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }
}