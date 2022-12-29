package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Area;
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

    public long getInmateCount() {
        return repository.count();
    }

    public Inmate getInmateById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public List<Inmate> getInmatesByAreaId(Area area) throws ResourceNotFoundException {
        return repository.findByAreaId(area.getId());
    }

    public Inmate updateInmate(Inmate inmate) throws ResourceNotFoundException {
        Inmate existingInmate = repository.findById(inmate.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingInmate == null){ return null; }
        
        existingInmate.setName(inmate.getName());
        existingInmate.setBirthDate(inmate.getBirthDate());
        existingInmate.setSolitary(inmate.getSolitary());
        existingInmate.setEntryDate(inmate.getEntryDate());
        existingInmate.setAreaId(inmate.getAreaId());
        if (existingInmate.getSolitary() == true) {
            existingInmate.setAreaId(10); // Send to Solitary
        }
        // Must contact datagen
        existingInmate.setDanger(inmate.getDanger());
        existingInmate.setWorkLogIds(inmate.getWorkLogIds());
        existingInmate.setMoveLogIds(inmate.getMoveLogIds());
        existingInmate.setHealthLogId(inmate.getHealthLogId());
        existingInmate.setSentenceEnd(inmate.getSentenceEnd()); 
        
        return repository.save(existingInmate);
    }

    public long getNextId() {
        long max_id = 0;
        for (Inmate inmate : getInmates()) {
            long id = inmate.getId();
            if (id > max_id) {
                max_id = id;
            }
        }
        return max_id+1;
    }
}
