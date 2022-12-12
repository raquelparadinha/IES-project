package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.HealthLog;
import ies.grupo51.lockedin.repositories.HealthLogRepository;

@Service
public class HealthLogService {
    
    @Autowired
    private HealthLogRepository repository;

    public HealthLog saveHealthLog(HealthLog healthLog){
        return repository.save(healthLog);
    }

    public List<HealthLog> saveHealthLog(Set<HealthLog> healthLog) {
        return repository.saveAll(healthLog);
    }

    public List<HealthLog> getHealthLogs() {
        return repository.findAll();
    }

    public HealthLog getHealthLogById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public HealthLog getHealthLogByInmateId(long inmateId) {
        return repository.findByInmateId(inmateId);
    }

    public HealthLog updateHealthLog(HealthLog healthLog) throws ResourceNotFoundException {
        HealthLog existingHealthLog = repository.findById(healthLog.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingHealthLog == null){ return null; }
        
        existingHealthLog.setCholesterol(healthLog.getCholesterol());
        existingHealthLog.setGlicose(healthLog.getGlicose());
        existingHealthLog.setHeartBeat(healthLog.getHeartBeat());
        existingHealthLog.setInmateId(healthLog.getInmateId());
        existingHealthLog.setStress(healthLog.getStress());
        existingHealthLog.setToxicScreen(healthLog.getToxicScreen());
        existingHealthLog.setUricAcid(healthLog.getUricAcid());
        
        return repository.save(existingHealthLog);
    }
}
