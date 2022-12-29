package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.RiotAlert;
import ies.grupo51.lockedin.repositories.RiotAlertRepository;

@Service
public class RiotAlertService {
    
    @Autowired
    private RiotAlertRepository repository;
    
    public RiotAlert saveRiotAlert(RiotAlert riotAlert){
        return repository.save(riotAlert);
    }

    public List<RiotAlert> saveRiotAlerts(Set<RiotAlert> riotAlerts) {
        return repository.saveAll(riotAlerts);
    }

    public List<RiotAlert> getRiotAlerts() {
        return repository.findAll();
    }

    public RiotAlert getRiotAlertById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }
}
