package ies.grupo51.lockedin.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.services.AreaService;

@CrossOrigin
@RestController
@RequestMapping("/api/areas")
public class AreaController {
    
    // SERVICES

    @Autowired
    private AreaService areaService;

    // GET METHODS

    @GetMapping("/")
    public ResponseEntity<List<Area>> getAreas() {
        return ResponseEntity.ok().body(areaService.getAreas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaUsingId(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(areaService.getAreaById(id));
    }

}
