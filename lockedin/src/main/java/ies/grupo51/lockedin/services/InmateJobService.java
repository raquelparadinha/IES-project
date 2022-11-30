package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.InmateJob;
import ies.grupo51.lockedin.repositories.InmateJobRepository;

@Service
public class InmateJobService {
    
    @Autowired
    private InmateJobRepository repository;

    public InmateJob saveInmateJob(InmateJob inmateJob){
        return repository.save(inmateJob);
    }

    public List<InmateJob> saveInmateJobs(Set<InmateJob> inmateJobs) {
        return repository.saveAll(inmateJobs);
    }

    public List<InmateJob> getInmateJobs() {
        return repository.findAll();
    }

    public InmateJob getInmateJobById(long id) {
        return repository.findById(id).orElse(null);
    }

    public InmateJob updatInmateJob(InmateJob inmateJob) {
        InmateJob existingInmateJob = repository.findById(inmateJob.getId()).orElse(null);
        
        if (existingInmateJob == null){ return null; }
        
        existingInmateJob.setDescription(inmateJob.getDescription());
        existingInmateJob.setDuration(inmateJob.getDuration());
        existingInmateJob.setEnd_time(inmateJob.getEnd_time());
        existingInmateJob.setStart_time(inmateJob.getStart_time());
        existingInmateJob.setWorking_station(inmateJob.getWorking_station());
        
        return repository.save(existingInmateJob);
    }
}