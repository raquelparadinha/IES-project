package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.GuardService;

@CrossOrigin
@RestController
@RequestMapping("/api/guard")
public class GuardController {

    // SERVICES

    @Autowired 
    private GuardService guardService;
    @Autowired 
    private AreaService areaService;

    // GET METHODS

    @GetMapping("")
    public ResponseEntity<List<HashMap<String, Object>>> getGuards() {
        List<HashMap<String, Object>> data = new ArrayList<>();
        List<Area> areas = areaService.getAreas();
        for (Guard guard : guardService.getGuards()) {
            HashMap<String, Object> insert = new HashMap<>();
            insert.put("id", guard.getId());
            insert.put("name", guard.getName());
            insert.put("email", guard.getEmail());
            insert.put("phone", guard.getPhone());
            insert.put("birthdate", guard.getBirthdate());
            insert.put("areaName", "TBD");
            for (Area area : areas) {
                if (area.getId() == guard.getAreaId()) {
                    insert.put("areaName", area.getName());
                }
            }
            insert.put("password", guard.getPassword());
            insert.put("roles", guard.getRoles());
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Guard data = guardService.getGuardById(id);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}/sidebar")
    public ResponseEntity<List<String>> getGuardColleagues(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Guard guard = guardService.getGuardById(id);
        List<String> data = new ArrayList<>();
        data.add(areaService.getAreaById(guard.getAreaId()).getAccess()?"Open":"Closed");
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
        guard.setAreaId(guardService.getNextId());
        return ResponseEntity.ok(guardService.saveGuard(guard));
    }
}
