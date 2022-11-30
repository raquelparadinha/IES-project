package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Warden getWardenById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Warden updateWarden(Warden warden) {
        Warden existingWarden = repository.findById(warden.getId()).orElse(null);
        
        if (existingWarden == null){ return null; }
        
        existingWarden.setName(warden.getName());
        existingWarden.setEmail(warden.getEmail());
        existingWarden.setPhone(warden.getPhone());
        existingWarden.setBirth_date(warden.getBirth_date());
        
        return repository.save(existingWarden);
    }
}
