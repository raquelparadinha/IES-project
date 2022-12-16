package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

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
import ies.grupo51.lockedin.models.HealthLog;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.models.MoveSensorLog;
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.HealthLogService;
import ies.grupo51.lockedin.services.InmateService;
import ies.grupo51.lockedin.services.MoveSensorLogService;
import ies.grupo51.lockedin.services.MoveSensorService;

@CrossOrigin
@RestController
@RequestMapping("/api/inmate")
public class InmateController {
    
    // SERVICES

    @Autowired
    private InmateService inmateService;

    @Autowired
    private HealthLogService healthLogService;

    @Autowired
    private MoveSensorLogService moveSensorLogService;

    @Autowired
    private MoveSensorService moveSensorService;

    @Autowired
    private AreaService areaService;

    // GET METHODS

    @GetMapping("") 
    public  ResponseEntity<List<Inmate>> getInmates() {

        return ResponseEntity.ok().body(inmateService.getInmates());
    }
    
    @GetMapping("/number") 
    public  ResponseEntity<Integer> getInmatesNumber() {
        return ResponseEntity.ok().body(inmateService.getInmates().size());
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Inmate> getInmateById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(inmateService.getInmateById(id));
    }

    @GetMapping("/leaving") 
    public ResponseEntity<List<Inmate>> getLeavingInmates() {
        List<Inmate> data = inmateService.getInmates();
        data.sort(((inmate1, inmate2) -> inmate1.getSentenceEnd().compareTo(inmate2.getSentenceEnd())));
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/leaving/{number}")
    public ResponseEntity<List<Inmate>> getLeavingInmatesNumber(@PathVariable(value = "number") int number) {
        List<Inmate> inmates = inmateService.getInmates();
        inmates.sort(((inmate1, inmate2) -> inmate1.getSentenceEnd().compareTo(inmate2.getSentenceEnd())));
        List<Inmate> data = new ArrayList<>();
        for (Inmate inmate : inmates) {
            if (number == data.size()) {
                return ResponseEntity.ok().body(data);
            }
            data.add(inmate);
        }
        return ResponseEntity.ok().body(data);
        
    }
    
    // HEALTH /////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/all/health")
    public ResponseEntity<List<HealthLog>> getAllHealLogs() {
        return ResponseEntity.ok().body(healthLogService.getHealthLogs());
    }

    @GetMapping("/{id}/health")
    public ResponseEntity<List<HealthLog>> getHealthLogsOfInmate(@PathVariable(value = "id") Long id) {
        List<HealthLog> healthLogs =  healthLogService.getHealthLogByInmateId(id);
        healthLogs.sort((healthLog1,healthLog2) -> healthLog1.getTimestamp().compareTo(healthLog2.getTimestamp()));
        return ResponseEntity.ok().body(healthLogs);
    }

    @GetMapping("/{id}/health/last")
    public ResponseEntity<HealthLog> getLastHealthLogOfInmate(@PathVariable(value = "id") Long id) {
        List<HealthLog> healthLogs =  healthLogService.getHealthLogByInmateId(id);
        healthLogs.sort((healthLog1,healthLog2) -> healthLog1.getTimestamp().compareTo(healthLog2.getTimestamp()));
        if (healthLogs.size() > 0){
            return ResponseEntity.ok().body(healthLogs.get(0));
        }
        return ResponseEntity.ok().body(null);
    }


    @GetMapping("/all/health/heartbeat/data")
    public ResponseEntity<List<HashMap<String, Integer>>> getDataHeartBeat() throws ResourceNotFoundException {
        List<HashMap<String, Integer>> data = new ArrayList<>();
        HashMap<Integer, Integer> valueQuantity = new HashMap<>();
        List<HealthLog> healthLogs = healthLogService.getHealthLogs();
        for (HealthLog healthLog : healthLogs) {
            Integer integer = healthLog.getHeartBeat();
            valueQuantity.putIfAbsent(integer, 0);
            valueQuantity.put(integer, valueQuantity.get(integer)+1);
        }
        for (Integer key : new TreeSet<>(valueQuantity.keySet())) {
            HashMap<String, Integer> insert = new HashMap<>();
            insert.put("value", key);
            insert.put("qty", valueQuantity.get(key));
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/all/health/stress/data")
    public ResponseEntity<List<HashMap<String, Integer>>> getDataStress() throws ResourceNotFoundException {
        List<HashMap<String, Integer>> data = new ArrayList<>();
        HashMap<Integer, Integer> valueQuantity = new HashMap<>();
        List<HealthLog> healthLogs = healthLogService.getHealthLogs();
        for (HealthLog healthLog : healthLogs) {
            Integer integer = healthLog.getStress();
            valueQuantity.putIfAbsent(integer, 0);
            valueQuantity.put(integer, valueQuantity.get(integer)+1);
        }
        for (Integer key : new TreeSet<>(valueQuantity.keySet())) {
            HashMap<String, Integer> insert = new HashMap<>();
            insert.put("value", key);
            insert.put("qty", valueQuantity.get(key));
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/all/health/glicose/data")
    public ResponseEntity<List<HashMap<String, Integer>>> getDataGlicose() throws ResourceNotFoundException {
        List<HashMap<String, Integer>> data = new ArrayList<>();
        HashMap<Integer, Integer> valueQuantity = new HashMap<>();
        List<HealthLog> healthLogs = healthLogService.getHealthLogs();
        for (HealthLog healthLog : healthLogs) {
            Integer integer = healthLog.getGlicose();
            valueQuantity.putIfAbsent(integer, 0);
            valueQuantity.put(integer, valueQuantity.get(integer)+1);
        }
        for (Integer key : new TreeSet<>(valueQuantity.keySet())) {
            HashMap<String, Integer> insert = new HashMap<>();
            insert.put("value", key);
            insert.put("qty", valueQuantity.get(key));
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/all/health/uricacid/data")
    public ResponseEntity<List<HashMap<String, Integer>>> getDataUricacid() throws ResourceNotFoundException {
        List<HashMap<String, Integer>> data = new ArrayList<>();
        HashMap<Integer, Integer> valueQuantity = new HashMap<>();
        List<HealthLog> healthLogs = healthLogService.getHealthLogs();
        for (HealthLog healthLog : healthLogs) {
            Integer integer = healthLog.getUricAcid();
            valueQuantity.putIfAbsent(integer, 0);
            valueQuantity.put(integer, valueQuantity.get(integer)+1);
        }
        for (Integer key : new TreeSet<>(valueQuantity.keySet())) {
            HashMap<String, Integer> insert = new HashMap<>();
            insert.put("value", key);
            insert.put("qty", valueQuantity.get(key));
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/all/health/cholesterol/data")
    public ResponseEntity<List<HashMap<String, Integer>>> getDataCholesterol() throws ResourceNotFoundException {
        List<HashMap<String, Integer>> data = new ArrayList<>();
        HashMap<Integer, Integer> valueQuantity = new HashMap<>();
        List<HealthLog> healthLogs = healthLogService.getHealthLogs();
        for (HealthLog healthLog : healthLogs) {
            Integer integer = healthLog.getCholesterol();
            valueQuantity.putIfAbsent(integer, 0);
            valueQuantity.put(integer, valueQuantity.get(integer)+1);
        }
        for (Integer key : new TreeSet<>(valueQuantity.keySet())) {
            HashMap<String, Integer> insert = new HashMap<>();
            insert.put("value", key);
            insert.put("qty", valueQuantity.get(key));
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/all/health/toxicscreen/data")
    public ResponseEntity<List<HashMap<String, Integer>>> getDataToxicscreen() throws ResourceNotFoundException {
        List<HashMap<String, Integer>> data = new ArrayList<>();
        HashMap<Integer, Integer> valueQuantity = new HashMap<>();
        List<HealthLog> healthLogs = healthLogService.getHealthLogs();
        for (HealthLog healthLog : healthLogs) {
            Integer integer = healthLog.getToxicScreen();
            valueQuantity.putIfAbsent(integer, 0);
            valueQuantity.put(integer, valueQuantity.get(integer)+1);
        }
        for (Integer key : new TreeSet<>(valueQuantity.keySet())) {
            HashMap<String, Integer> insert = new HashMap<>();
            insert.put("value", key);
            insert.put("qty", valueQuantity.get(key));
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }
    
    // MOVE /////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{id}/moves")
    public ResponseEntity<List<String>> getAreasInmateHaveBeenTo(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Inmate inmate = inmateService.getInmateById(id);
        List<String> data = new ArrayList<>();
        for (Long moveSensorLogId : inmate.getMoveLogIds()) {
            MoveSensorLog moveSensorLog = moveSensorLogService.getMoveSensorLogById(moveSensorLogId);
            MoveSensor moveSensor = moveSensorService.getMoveSensorById(moveSensorLog.getSensorId());
            Area area = areaService.getAreaById(moveSensor.getEntryAreaId());
            data.add(area.getName());
        }
        return ResponseEntity.ok().body(data);
    }

    // PUT METHOD

    @PutMapping("/{id}")
    public ResponseEntity<Inmate> updateInmate(@PathVariable(value = "id") Long id, @Valid @RequestBody Inmate inmateDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(inmateService.updateInmate(inmateDetails));
    }

    // POST METHOD

    @PostMapping("")
    public ResponseEntity<Inmate> createInmate(@Valid @RequestBody Inmate inmate){
        inmate.setId(inmateService.getNextId());
        return ResponseEntity.ok(inmateService.saveInmate(inmate));
    }
}
