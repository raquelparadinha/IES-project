package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Guard getGuardById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Guard updateGuard(Guard guard) {
        Guard existingGuard = repository.findById(guard.getId()).orElse(null);
        
        if (existingGuard == null){ return null; }

        existingGuard.setName(guard.getName());
        existingGuard.setEmail(guard.getEmail());
        existingGuard.setPhone(guard.getPhone());
        existingGuard.setBirth_date(guard.getBirth_date());
        existingGuard.setAreas(guard.getAreas());
        
        return repository.save(existingGuard);
    }
}
