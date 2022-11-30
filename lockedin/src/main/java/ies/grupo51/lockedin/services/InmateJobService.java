package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.Workstation;
import ies.grupo51.lockedin.repositories.InmateJobRepository;

@Service
public class InmateJobService {
    
    @Autowired
    private InmateJobRepository repository;

    public Workstation saveInmateJob(Workstation inmateJob){
        return repository.save(inmateJob);
    }

    public List<Workstation> saveInmateJobs(Set<Workstation> inmateJobs) {
        return repository.saveAll(inmateJobs);
    }

    public List<Workstation> getInmateJobs() {
        return repository.findAll();
    }

    public Workstation getInmateJobById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Workstation updatInmateJob(Workstation inmateJob) {
        Workstation existingInmateJob = repository.findById(inmateJob.getId()).orElse(null);
        
        if (existingInmateJob == null){ return null; }
        
        existingInmateJob.setDescription(inmateJob.getDescription());
        existingInmateJob.setDuration(inmateJob.getDuration());
        existingInmateJob.setEnd_time(inmateJob.getEnd_time());
        existingInmateJob.setStart_time(inmateJob.getStart_time());
        existingInmateJob.setWorking_station(inmateJob.getWorking_station());
        
        return repository.save(existingInmateJob);
    }
}