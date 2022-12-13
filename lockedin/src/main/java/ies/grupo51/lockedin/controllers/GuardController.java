package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("")
    public ResponseEntity<List<Guard>> getGuards() {
        return ResponseEntity.ok().body(guardService.getGuards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Guard data = guardService.getGuardById(id);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}/colleagues")
    public ResponseEntity<List<String>> getGuardColleagues(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Guard guard = guardService.getGuardById(id);
        List<String> data = new ArrayList<>();

        for (Guard otherGuard : guardService.getGuards()) {
            if (otherGuard.getId() != id && otherGuard.getAreaId() == guard.getAreaId()) {
                data.add(otherGuard.getName());
            }
        }
        return ResponseEntity.ok().body(data);
    }

    // PUT METHOD

    @PutMapping("/{id}")
    public ResponseEntity<Guard> updateGuard(@PathVariable(value = "id") Long id, @Valid @RequestBody Guard guardDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(guardService.updateGuard(guardDetails));
    }

    // POST METHOD

    @PostMapping("")
    public ResponseEntity<Guard> createGuard(@Valid @RequestBody Guard guard){
        return ResponseEntity.ok(guardService.saveGuard(guard));
    }
}
