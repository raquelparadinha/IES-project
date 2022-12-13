package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.EstrilhoAlert;
import ies.grupo51.lockedin.repositories.EstrilhoAlertRepository;

@Service
public class EstrilhoAlertService {
    
    @Autowired
    private EstrilhoAlertRepository repository;
    
    public EstrilhoAlert saveAlert(EstrilhoAlert estrilhoAlert){
        return repository.save(estrilhoAlert);
    }

    public List<EstrilhoAlert> saveEstrilhoAlerts(Set<EstrilhoAlert> estrilhoAlerts) {
        return repository.saveAll(estrilhoAlerts);
    }

    public List<EstrilhoAlert> getEstrilhoAlerts() {
        return repository.findAll();
    }

    public EstrilhoAlert getEstrilhoAlertById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }
}
