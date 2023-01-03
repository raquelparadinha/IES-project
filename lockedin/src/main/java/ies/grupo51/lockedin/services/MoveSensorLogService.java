package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.MoveSensorLog;
import ies.grupo51.lockedin.repositories.MoveSensorLogRepository;

@Service
public class MoveSensorLogService {
    
    @Autowired private MoveSensorLogRepository repository;
    
    public MoveSensorLog saveMoveSensorLog(MoveSensorLog moveSensorLog) {
        if (repository.count() > 5000) {
            repository.deleteById(getFirstId());
        }
        return repository.save(moveSensorLog);
    }

    public List<MoveSensorLog> saveMoveSensorLogs(Set<MoveSensorLog> moveSensorLog) {
        return repository.saveAll(moveSensorLog);
    }

    public List<MoveSensorLog> getMoveSensorLogs() {
        return repository.findAll();
    }

    public MoveSensorLog getMoveSensorLogById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public long getNextId() {
        long max_id = 0;
        for (MoveSensorLog alert : getMoveSensorLogs()) {
            long id = alert.getId();
            if (id > max_id) {
                max_id = id;
            }
        }
        
        return max_id+1;
    }

    public long getFirstId() {
        long min_id = 0;
        for (MoveSensorLog alert : getMoveSensorLogs()) {
            long id = alert.getId();
            if (id < min_id) {
                min_id = id;
            }
        }
        
        return min_id;
    }
}
