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
    
}
