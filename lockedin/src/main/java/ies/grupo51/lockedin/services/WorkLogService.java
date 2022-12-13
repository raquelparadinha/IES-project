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

    public long getNextId() {
        long max_id = 1;
        for (WorkLog alert : getWorkLogs()) {
            long id = alert.getId();
            if (id > max_id) {
                max_id = id;
            }
        }
        return max_id+1;
    }
}
