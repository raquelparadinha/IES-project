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
import ies.grupo51.lockedin.models.HealthLog;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.services.HealthLogService;
import ies.grupo51.lockedin.services.InmateService;

@CrossOrigin
@RestController
@RequestMapping("/api/inmate")
public class InmateController {
    
    // SERVICES

    @Autowired
    private InmateService inmateService;

    @Autowired
    private HealthLogService healthLogService;

    // GET METHODS

    @GetMapping("/")
    public ResponseEntity<List<Inmate>> getInmates() {
        return ResponseEntity.ok().body(inmateService.getInmates());
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
        for (int i = 0; i < number; i++) {
            data.add(inmates.get(i));
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/health/heartbeat/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralHeartBeat() throws ResourceNotFoundException {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> heartbeatlist = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            HealthLog healthLog = healthLogService.getHealthLogById(inmate.getHealthLogId());
            if (healthLog != null) {
                heartbeatlist.add(healthLog.getHeartBeat());
            }
        }
        for (Integer integer : heartbeatlist) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/health/stress/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralStress() throws ResourceNotFoundException {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> stressList = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            HealthLog healthLog = healthLogService.getHealthLogById(inmate.getHealthLogId());
            if (healthLog != null) {
                stressList.add(healthLog.getStress());
            }
        }
        for (Integer integer : stressList) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/health/glicoselevel/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralGlicoseLevel() throws ResourceNotFoundException {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> glicoseList = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            HealthLog healthLog = healthLogService.getHealthLogById(inmate.getHealthLogId());
            if (healthLog != null) {
                glicoseList.add(healthLog.getGlicose());
            }
        }
        for (Integer integer : glicoseList) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/health/uricacid/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralUricAcid() throws ResourceNotFoundException {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> uricacidList = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            HealthLog healthLog = healthLogService.getHealthLogById(inmate.getHealthLogId());
            if (healthLog != null) {
                uricacidList.add(healthLog.getUricAcid());
            }
        }
        for (Integer integer : uricacidList) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/health/cholesterol/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralCholesterol() throws ResourceNotFoundException {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> cholesterollist = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            HealthLog healthLog = healthLogService.getHealthLogById(inmate.getHealthLogId());
            if (healthLog != null) {
                cholesterollist.add(healthLog.getCholesterol());
            }
        }
        for (Integer integer : cholesterollist) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/health/toxicscreen/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralToxicScreen() throws ResourceNotFoundException {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> toxicscreenlist = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            HealthLog healthLog = healthLogService.getHealthLogById(inmate.getHealthLogId());
            if (healthLog != null) {
                toxicscreenlist.add(healthLog.getToxicScreen());
            }
        }
        for (Integer integer : toxicscreenlist) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
}
