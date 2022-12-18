package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.models.WorkLog;
import ies.grupo51.lockedin.models.WorkStation;
import ies.grupo51.lockedin.services.InmateService;
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

    @Autowired
    public InmateService inmateService;

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
    public ResponseEntity<List<HashMap<String, Integer>>> getWorkStationWorkLogs(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        List<HashMap<String,Integer>> data = new ArrayList<>();
        List<Long> workLogIds = workStationService.getWorkStationById(id).getWorkLogIds();
        for (int i = 0; i < workLogIds.size(); i++) {
            HashMap<String,Integer> insert = new HashMap<>();
            insert.put("x", i);
            insert.put("y", workLogService.getWorkLogById(workLogIds.get(i)).getQuota());
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("worklog/{id}/inmate")
    public ResponseEntity<HashMap<String, Object>> getWorkLogInmateInfo(@PathVariable(value = "id") long id ) throws ResourceNotFoundException{
        HashMap<String, Object> data = new HashMap<>();
        data.put("workLogId", id);
        WorkLog workLog = workLogService.getWorkLogById(id);
        Inmate inmate = inmateService.getInmateById(workLog.getInmateId());
        data.put("inmateName", inmate.getName());
        return ResponseEntity.ok().body(data);
    }
}
