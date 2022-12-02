package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Healthcheck;
import ies.grupo51.lockedin.repositories.HealthcheckRepository;

@Service
public class HealthcheckService {
    
    @Autowired
    private HealthcheckRepository repository;

    public Healthcheck saveHealthcheck(Healthcheck healthcheck){
        return repository.save(healthcheck);
    }

    public List<Healthcheck> saveHealthchecks(Set<Healthcheck> healthchecks) {
        return repository.saveAll(healthchecks);
    }

    public List<Healthcheck> getHealthchecks() {
        return repository.findAll();
    }

    public Healthcheck getHealthcheckById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Healthcheck updateHealthcheck(Healthcheck healthcheck) throws ResourceNotFoundException {
        Healthcheck existingHealthcheck = repository.findById(healthcheck.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingHealthcheck == null){ return null; }

        existingHealthcheck.setBiometrics(healthcheck.getBiometrics());
        existingHealthcheck.setInmate_id(healthcheck.getInmate_id());

        return repository.save(existingHealthcheck);
    }
}
