package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.WorkStation;
import ies.grupo51.lockedin.repositories.WorkStationRepository;

@Service
public class WorkStationService {
    
    @Autowired
    private WorkStationRepository repository;

    public WorkStation saveWorkStation(WorkStation workStation){
        return repository.save(workStation);
    }

    public List<WorkStation> saveWorkStations(Set<WorkStation> workStations) {
        return repository.saveAll(workStations);
    }

    public List<WorkStation> getWorkStations() {
        return repository.findAll();
    }

    public WorkStation getWorkStationById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public WorkStation updatWorkStation(WorkStation workStation) throws ResourceNotFoundException {
        WorkStation existingWorkStation = repository.findById(workStation.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingWorkStation == null){ return null; }
        
        existingWorkStation.setName(workStation.getName());
        existingWorkStation.setDescription(workStation.getDescription());
        existingWorkStation.setWorkLogIds(workStation.getWorkLogIds());
        existingWorkStation.setExpectedQuota(workStation.getExpectedQuota());
        
        return repository.save(existingWorkStation);
    }
}