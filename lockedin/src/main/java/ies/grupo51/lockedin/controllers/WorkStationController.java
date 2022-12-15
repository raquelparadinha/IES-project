package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.WorkLog;
import ies.grupo51.lockedin.models.WorkStation;
import ies.grupo51.lockedin.services.WorkLogService;
import ies.grupo51.lockedin.services.WorkStationService;

@CrossOrigin
@RestController
@RequestMapping("/api/workstation")
public class WorkStationController {
    
    // SERVICES

    @Autowired
    public WorkStationService workStationService;

    @Autowired
    public WorkLogService workLogService;

    // GET METHODS

    @GetMapping("")
    public ResponseEntity<List<WorkStation>> getAllWorkStations() {
        return ResponseEntity.ok().body(workStationService.getWorkStations());
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkStation> getWorkStationById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(workStationService.getWorkStationById(id));
    }

    @GetMapping("{id}/worklogs")
    public ResponseEntity<List<WorkLog>> getWorkStationWorkLogs(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        List<WorkLog> data = new ArrayList<>();
        for (long workLogId : workStationService.getWorkStationById(id).getWorkLogIds()) {
            data.add(workLogService.getWorkLogById(workLogId));
        }
        return ResponseEntity.ok().body(data);
    }
}
