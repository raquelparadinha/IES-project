package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.HealthSensorData;
import ies.grupo51.lockedin.repositories.HealthSensorDataRepository;

@Service
public class HealthSensorDataService {
    
    @Autowired
    private HealthSensorDataRepository repository;

    public HealthSensorData saveHealthSensorData(HealthSensorData healthSensorData){
        return repository.save(healthSensorData);
    }

    public List<HealthSensorData> saveHealthSensorDatas(Set<HealthSensorData> healthSensorDatas) {
        return repository.saveAll(healthSensorDatas);
    }

    public List<HealthSensorData> getHealthSensorDatas() {
        return repository.findAll();
    }

    public HealthSensorData getHealthSensorDataById(long id) {
        return repository.findById(id).orElse(null);
    }

    public HealthSensorData updateHealthSensorData(HealthSensorData healthSensorData) {
        HealthSensorData existingHealthSensorData = repository.findById(healthSensorData.getId()).orElse(null);
        
        if (existingHealthSensorData == null){ return null; }

        existingHealthSensorData.setBiometrics(healthSensorData.getBiometrics());
        existingHealthSensorData.setInmate_id(healthSensorData.getInmate_id());

        return repository.save(existingHealthSensorData);
    }
}
