package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.MoveSensorData;
import ies.grupo51.lockedin.repositories.MoveSensorDataRepository;

@Service
public class MoveSensorDataService {
    
    @Autowired
    private MoveSensorDataRepository repository;
    
    public MoveSensorData saveMoveSensorData(MoveSensorData moveSensorData){
        return repository.save(moveSensorData);
    }

    public List<MoveSensorData> saveMoveSensorData(Set<MoveSensorData> moveSensorDatas) {
        return repository.saveAll(moveSensorDatas);
    }

    public List<MoveSensorData> getMoveSensorData() {
        return repository.findAll();
    }

    public MoveSensorData getMoveSensorDataById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public MoveSensorData updateMoveSensorData(MoveSensorData moveSensorData) throws ResourceNotFoundException {
        MoveSensorData existingMoveSensorData = repository.findById(moveSensorData.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingMoveSensorData == null){ return null; }
        
        existingMoveSensorData.setInmate_id(moveSensorData.getInmate_id());
        existingMoveSensorData.setMove_sensor(moveSensorData.getMove_sensor());
        
        return repository.save(existingMoveSensorData);
    }
}
