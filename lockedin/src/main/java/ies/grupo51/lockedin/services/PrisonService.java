package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Prison getPrisonById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Prison updatPrison(Prison prison) {
        Prison existingPrison = repository.findById(prison.getId()).orElse(null);
        
        if (existingPrison == null){ return null; }
        
        existingPrison.setAreas(prison.getAreas());
        existingPrison.setGuards(prison.getGuards());
        existingPrison.setNum_areas(prison.getNum_areas());
        existingPrison.setNum_guards(prison.getNum_guards());
        existingPrison.setInmates(prison.getInmates());
        existingPrison.setName(prison.getName());
        existingPrison.setWarden(prison.getWarden());
        
        return repository.save(existingPrison);
    }
}