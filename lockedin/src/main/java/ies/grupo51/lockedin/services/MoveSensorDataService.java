package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MoveSensorData getMoveSensorDataById(long id) {
        return repository.findById(id).orElse(null);
    }

    public MoveSensorData updateMoveSensorData(MoveSensorData moveSensorData) {
        MoveSensorData existingMoveSensorData = repository.findById(moveSensorData.getId()).orElse(null);
        
        if (existingMoveSensorData == null){ return null; }
        
        existingMoveSensorData.setInmate_id(moveSensorData.getInmate_id());
        existingMoveSensorData.setNew_area(moveSensorData.getNew_area());
        
        return repository.save(existingMoveSensorData);
    }
}
