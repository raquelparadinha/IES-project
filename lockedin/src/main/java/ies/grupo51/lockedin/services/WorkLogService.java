package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.WorkLog;
import ies.grupo51.lockedin.repositories.WorkLogRepository;

@Service
public class WorkLogService {
    
    @Autowired
    private WorkLogRepository repository;

    public WorkLog saveWorkLog(WorkLog workLog){
        return repository.save(workLog);
    }

    public List<WorkLog> saveWorkLogs(Set<WorkLog> workLogs) {
        return repository.saveAll(workLogs);
    }

    public List<WorkLog> getWorkLogs() {
        return repository.findAll();
    }

    public WorkLog getWorkLogById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public WorkLog updatWorkLog(WorkLog workLog) throws ResourceNotFoundException {
        WorkLog existingWorkLog = repository.findById(workLog.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingWorkLog == null){ return null; }
        
        existingWorkLog.setQuota(workLog.getQuota());
        existingWorkLog.setQuota(workLog.getQuota());
        existingWorkLog.setWorkStationId(workLog.getWorkStationId());
        
        return repository.save(existingWorkLog);
    }
}