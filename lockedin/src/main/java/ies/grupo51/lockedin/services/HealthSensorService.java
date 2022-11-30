package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.HealthSensor;
import ies.grupo51.lockedin.repositories.HealthSensorRepository;

@Service
public class HealthSensorService {

    @Autowired
    private HealthSensorRepository repository;

    public HealthSensor saveHealthSensor(HealthSensor healthSensor){
        return repository.save(healthSensor);
    }

    public List<HealthSensor> saveHealthSensors(Set<HealthSensor> healthSensors) {
        return repository.saveAll(healthSensors);
    }

    public List<HealthSensor> getHealthSensors() {
        return repository.findAll();
    }

    public HealthSensor getHealthSensorById(long id) {
        return repository.findById(id).orElse(null);
    }

    public HealthSensor updatHealthSensor(HealthSensor healthSensor) {
        HealthSensor existingHealthSensor = repository.findById(healthSensor.getId()).orElse(null);
        
        if (existingHealthSensor == null){ return null; }
        
        existingHealthSensor.setArea(healthSensor.getArea());
        existingHealthSensor.setLogs(healthSensor.getLogs());
        existingHealthSensor.setName(healthSensor.getName());
        
        return repository.save(existingHealthSensor);
    }
}
