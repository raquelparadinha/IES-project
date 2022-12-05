package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    public List<Healthcheck> saveHealthcheck(Set<Healthcheck> healthcheck) {
        return repository.saveAll(healthcheck);
    }

    public List<Healthcheck> getHealthcheck() {
        return repository.findAll();
    }

    public Healthcheck getHealthcheckById(UUID id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Healthcheck updateHealthcheck(Healthcheck healthcheck) throws ResourceNotFoundException {
        Healthcheck existingHealthcheck = repository.findById(healthcheck.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingHealthcheck == null){ return null; }
        
        existingHealthcheck.setCholesterol(healthcheck.getCholesterol());
        existingHealthcheck.setGlicose_level(healthcheck.getGlicose_level());
        existingHealthcheck.setHeart_beat(healthcheck.getHeart_beat());
        existingHealthcheck.setInmate_id(healthcheck.getInmate_id());
        existingHealthcheck.setStress_level(healthcheck.getStress_level());
        existingHealthcheck.setToxic_screen(healthcheck.getToxic_screen());
        existingHealthcheck.setUric_acid(healthcheck.getUric_acid());
        
        return repository.save(existingHealthcheck);
    }
}
