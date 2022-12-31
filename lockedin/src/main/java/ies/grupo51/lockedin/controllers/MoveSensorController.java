package ies.grupo51.lockedin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.comms.Sender;
import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.services.MoveSensorService;

@CrossOrigin
@RestController
@RequestMapping("/api/sensor")
public class MoveSensorController {
    
    // SERVICES
    @Autowired private MoveSensorService moveSensorService;

    
    @Autowired private Sender sender;

    // GET METHODS

    @GetMapping("")
    public ResponseEntity<List<MoveSensor>> getSensors() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(moveSensorService.getMoveSensors());
    } 


    @GetMapping("/{id}/lock")
    public ResponseEntity<MoveSensor> lockSensor(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        MoveSensor ms = moveSensorService.getMoveSensorById(id);
        
        if(!ms.isActive()) {
            return ResponseEntity.badRequest().body(ms);
        }

        ms.setActive(false);
        moveSensorService.updatMoveSensor(ms);
        sender.lockSensor(id);
        return ResponseEntity.ok().body(ms);
    }

    @GetMapping("/{id}/unlock")
    public ResponseEntity<MoveSensor> unlockSensor(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        MoveSensor ms = moveSensorService.getMoveSensorById(id);
        
        if(ms.isActive()) {
            return ResponseEntity.badRequest().body(ms);
        }

        ms.setActive(true);
        moveSensorService.updatMoveSensor(ms);
        sender.unlockSensor(id);
        return ResponseEntity.ok().body(ms);
    }
}
