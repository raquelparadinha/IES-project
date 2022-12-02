package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Biometrics;
import ies.grupo51.lockedin.repositories.BiometricsRepository;

@Service
public class BiometricsService {
    
    @Autowired
    private BiometricsRepository repository;

    public Biometrics saveBiometrics(Biometrics biometrics){
        return repository.save(biometrics);
    }

    public List<Biometrics> saveBiometrics(Set<Biometrics> biometrics) {
        return repository.saveAll(biometrics);
    }

    public List<Biometrics> getBiometrics() {
        return repository.findAll();
    }

    public Biometrics getBiometricsById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Biometrics updatBiometrics(Biometrics biometrics) throws ResourceNotFoundException {
        Biometrics existingBiometrics = repository.findById(biometrics.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingBiometrics == null){ return null; }
        
        existingBiometrics.setCholesterol(biometrics.getCholesterol());
        existingBiometrics.setGlicose_level(biometrics.getGlicose_level());
        existingBiometrics.setHeart_beat(biometrics.getHeart_beat());
        existingBiometrics.setInmate_id(biometrics.getInmate_id());
        existingBiometrics.setStress_level(biometrics.getStress_level());
        existingBiometrics.setToxic_screen(biometrics.getToxic_screen());
        existingBiometrics.setUric_acid(biometrics.getUric_acid());
        
        return repository.save(existingBiometrics);
    }
}
