package ies.grupo51.lockedin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import ies.grupo51.lockedin.models.Warden;
import ies.grupo51.lockedin.services.WardenService;


@CrossOrigin
@RestController
@RequestMapping("/api/warden")
public class WardenController {

    @Autowired
    private WardenService wardenService;

    @GetMapping("/profile")
    public ResponseEntity<Warden> getWarden() {
        Warden data = wardenService.getWardens().get(0);
        return ResponseEntity.ok().body(data);
    }
    
}
