package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.repositories.GuardRepository;

@Service
public class GuardService {
    
    @Autowired
    private GuardRepository repository;

    public Guard saveGuard(Guard guard){
        return repository.save(guard);
    }

    public List<Guard> saveGuards(Set<Guard> guards) {
        return repository.saveAll(guards);
    }

    public List<Guard> getGuards() {
        return repository.findAll();
    }

    public Guard getGuardById(UUID id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Guard updateGuard(Guard guard) throws ResourceNotFoundException {
        Guard existingGuard = repository.findById(guard.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingGuard == null){ return null; }

        existingGuard.setName(guard.getName());
        existingGuard.setEmail(guard.getEmail());
        existingGuard.setPhone(guard.getPhone());
        existingGuard.setBirth_date(guard.getBirth_date());
        existingGuard.setShifts(guard.getShifts());
        
        return repository.save(existingGuard);
    }
}
