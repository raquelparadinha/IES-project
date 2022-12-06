package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Warden;
import ies.grupo51.lockedin.repositories.WardenRepository;

@Service
public class WardenService {

    @Autowired
    private WardenRepository repository;

    public Warden saveWarden(Warden warden){
        return repository.save(warden);
    }

    public List<Warden> saveWarden(Set<Warden> wardens) {
        return repository.saveAll(wardens);
    }

    public List<Warden> getWardens() {
        return repository.findAll();
    }

    public Warden getWardenById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Warden updateWarden(Warden warden) throws ResourceNotFoundException {
        Warden existingWarden = repository.findById(warden.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingWarden == null){ return null; }
        
        existingWarden.setName(warden.getName());
        existingWarden.setEmail(warden.getEmail());
        existingWarden.setPhone(warden.getPhone());
        existingWarden.setBirthdate(warden.getBirthdate());
        existingWarden.setPassword(warden.getPassword());
        
        return repository.save(existingWarden);
    }
}
