package ies.grupo51.lockedin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.services.GuardService;

@CrossOrigin
@RestController
@RequestMapping("/api/guard")
public class GuardController {

    // SERVICES

    @Autowired 
    private GuardService guardService;

    // GET METHODS

    @GetMapping("/")
    public ResponseEntity<List<Guard>> getGuards() {
        List<Guard> data = guardService.getGuards();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Guard data = guardService.getGuardById(id);
        return ResponseEntity.ok().body(data);
    }
}
