package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Workstation;
import ies.grupo51.lockedin.repositories.WorkstationRepository;

@Service
public class WorkstationService {
    
    @Autowired
    private WorkstationRepository repository;

    public Workstation saveWorkstation(Workstation workstation){
        return repository.save(workstation);
    }

    public List<Workstation> saveWorkstations(Set<Workstation> workstations) {
        return repository.saveAll(workstations);
    }

    public List<Workstation> getWorkstations() {
        return repository.findAll();
    }

    public Workstation getWorkstationById(UUID id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Workstation updatWorkstation(Workstation workstation) throws ResourceNotFoundException {
        Workstation existingWorkstation = repository.findById(workstation.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingWorkstation == null){ return null; }
        
        existingWorkstation.setDescription(workstation.getDescription());
        existingWorkstation.setDuration(workstation.getDuration());
        existingWorkstation.setEnd_time(workstation.getEnd_time());
        existingWorkstation.setStart_time(workstation.getStart_time());
        existingWorkstation.setWorking_station(workstation.getWorking_station());
        
        return repository.save(existingWorkstation);
    }
}