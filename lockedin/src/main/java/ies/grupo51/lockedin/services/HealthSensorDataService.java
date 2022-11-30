package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.models.Healthcheck;
import ies.grupo51.lockedin.repositories.HealthSensorDataRepository;

@Service
public class HealthSensorDataService {
    
    @Autowired
    private HealthSensorDataRepository repository;

    public Healthcheck saveHealthSensorData(Healthcheck healthSensorData){
        return repository.save(healthSensorData);
    }

    public List<Healthcheck> saveHealthSensorDatas(Set<Healthcheck> healthSensorDatas) {
        return repository.saveAll(healthSensorDatas);
    }

    public List<Healthcheck> getHealthSensorDatas() {
        return repository.findAll();
    }

    public Healthcheck getHealthSensorDataById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Healthcheck updateHealthSensorData(Healthcheck healthSensorData) {
        Healthcheck existingHealthSensorData = repository.findById(healthSensorData.getId()).orElse(null);
        
        if (existingHealthSensorData == null){ return null; }

        existingHealthSensorData.setBiometrics(healthSensorData.getBiometrics());
        existingHealthSensorData.setInmate_id(healthSensorData.getInmate_id());

        return repository.save(existingHealthSensorData);
    }
}
