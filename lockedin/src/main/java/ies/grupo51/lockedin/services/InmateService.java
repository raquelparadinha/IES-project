package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.repositories.InmateRepository;

@Service
public class InmateService {
    
    @Autowired
    private InmateRepository repository;

    public Inmate saveInmate(Inmate inmate){
        return repository.save(inmate);
    }

    public List<Inmate> saveInmates(Set<Inmate> inmates) {
        return repository.saveAll(inmates);
    }

    public List<Inmate> getInmates() {
        return repository.findAll();
    }

    public Inmate getInmateById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Inmate updateInmate(Inmate inmate) {
        Inmate existingInmate = repository.findById(inmate.getId()).orElse(null);
        
        if (existingInmate == null){ return null; }
        
        existingInmate.setName(inmate.getName());
        existingInmate.setBirth_date(inmate.getBirth_date());
        existingInmate.setSolitary(inmate.getSolitary());
        existingInmate.setEntry_date(inmate.getEntry_date());
        existingInmate.setWorkstation(inmate.getWorkstation());
        existingInmate.setMove_logs(inmate.getMove_logs());
        existingInmate.setHealth_logs(inmate.getHealth_logs());
        existingInmate.setSentence_ending(inmate.getSentence_ending()); 
        
        return repository.save(existingInmate);
    }
}
