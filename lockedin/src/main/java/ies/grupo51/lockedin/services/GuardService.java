package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

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

    public Guard getGuardById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }
    
    public Guard getGuardByEmail(String email) throws ResourceNotFoundException {
        return repository.findByEmail(email);
    }

    public Guard updateGuard(Guard guard) throws ResourceNotFoundException {
        Guard existingGuard = repository.findById(guard.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingGuard == null){ return null; }

        existingGuard.setName(guard.getName());
        existingGuard.setEmail(guard.getEmail());
        existingGuard.setPhone(guard.getPhone());
        existingGuard.setAreaId(guard.getAreaId());
        existingGuard.setBirthdate(guard.getBirthdate());
        existingGuard.setPassword(guard.getPassword());
        
        return repository.save(existingGuard);
    }

    public long getNextId() {
        long max_id = 0;
        for (Guard guard : getGuards()) {
            long id = guard.getId();
            if (id > max_id) {
                max_id = id;
            }
        }
        return max_id+1;
    }
}
