package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Prison;
import ies.grupo51.lockedin.repositories.PrisonRepository;

@Service
public class PrisonService {
     
    @Autowired
    private PrisonRepository repository;

    public Prison savePrison(Prison prison){
        return repository.save(prison);
    }

    public List<Prison> savePrisons(Set<Prison> prisons) {
        return repository.saveAll(prisons);
    }

    public List<Prison> getPrisons() {
        return repository.findAll();
    }

    public Prison getPrisonById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Prison updatPrison(Prison prison) throws ResourceNotFoundException {
        Prison existingPrison = repository.findById(prison.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingPrison == null){ return null; }
        
        existingPrison.setName(prison.getName());
        existingPrison.setDescription(prison.getDescription());
        existingPrison.setAddress(prison.getAddress());
        
        return repository.save(existingPrison);
    }
}