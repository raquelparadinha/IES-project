package ies.grupo51.lockedin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Warden;
import ies.grupo51.lockedin.services.WardenService;


@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/warden")
public class WardenController {

    @Autowired
    private WardenService wardenService;

    @GetMapping("/profile")
    public ResponseEntity<Warden> getWarden() throws ResourceNotFoundException {
        Warden data = wardenService.getWardens().get(0);
        return ResponseEntity.ok().body(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warden> updateWarden(@PathVariable(value = "id") Long id, @Valid @RequestBody Warden wardenDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(wardenService.updateWarden(wardenDetails));
    }
}
