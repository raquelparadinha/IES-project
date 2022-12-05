package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Healthcheck;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.models.Workstation;
import ies.grupo51.lockedin.services.InmateService;

@CrossOrigin
@RestController
@RequestMapping("/api/inmate")
public class InmateController {
    
    // SERVICES

    @Autowired
    private InmateService inmateService;

    // GET METHODS

    @GetMapping("/")
    public ResponseEntity<List<Inmate>> getInmates() {
        return ResponseEntity.ok().body(inmateService.getInmates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inmate> getInmateById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(inmateService.getInmateById(id));
    }

    @GetMapping("/{inmate_id}/shifts")
    public ResponseEntity<Set<Workstation>> getInmateShifts(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Inmate inmate = inmateService.getInmateById(id);
        if (inmate != null) {
            return ResponseEntity.ok().body(inmate.getShifts());
        } else {
            return ResponseEntity.ok().body(null);
        }
        
    }

    @GetMapping("/{inmate_id}/shifts/{shift_id}")
    public ResponseEntity<Workstation> getShiftInmate(  @PathVariable(value = "id") long inmate_id,
                                                        @PathVariable(value = "id") long shift_id) 
                                                        throws ResourceNotFoundException {
        Inmate inmate = inmateService.getInmateById(inmate_id);
        if (inmate != null) {
            for (Workstation shift : inmate.getShifts()) {
                if (shift.getId() == shift_id) {
                    return ResponseEntity.ok().body(shift);
                }
            }
        } 
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/leaving")
    public ResponseEntity<List<Inmate>> getLeavingInmates() {
        List<Inmate> data = inmateService.getInmates();
        data.sort(((inmate1, inmate2) -> inmate1.getSentence_ending().compareTo(inmate2.getSentence_ending())));
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/leaving/{number}")
    public ResponseEntity<List<Inmate>> getLeavingInmatesNumber(@PathVariable(value = "number") int number) {
        List<Inmate> inmates = inmateService.getInmates();
        inmates.sort(((inmate1, inmate2) -> inmate1.getSentence_ending().compareTo(inmate2.getSentence_ending())));
        List<Inmate> data = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            data.add(inmates.get(i));
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/healthcheck/heart_beat/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralHeartBeat() {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> heartbeatlist = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            Healthcheck healthcheck = inmate.getLastHealthLog();
            if (healthcheck != null) {
                heartbeatlist.add(healthcheck.getHeart_beat());
            }
        }
        for (Integer integer : heartbeatlist) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/healthcheck/stress_level/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralStressLevel() {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> stresslevelList = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            Healthcheck healthcheck = inmate.getLastHealthLog();
            if (healthcheck != null) {
                stresslevelList.add(healthcheck.getStress_level());
            }
        }
        for (Integer integer : stresslevelList) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/healthcheck/glicose_level/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralGlicoseLevel() {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> glicoselevelList = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            Healthcheck healthcheck = inmate.getLastHealthLog();
            if (healthcheck != null) {
                glicoselevelList.add(healthcheck.getGlicose_level());
            }
        }
        for (Integer integer : glicoselevelList) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/healthcheck/uric_acid/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralUricAcid() {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> uricacidList = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            Healthcheck healthcheck = inmate.getLastHealthLog();
            if (healthcheck != null) {
                uricacidList.add(healthcheck.getUric_acid());
            }
        }
        for (Integer integer : uricacidList) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/healthcheck/cholesterol/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralCholesterol() {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> cholesterollist = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            Healthcheck healthcheck = inmate.getLastHealthLog();
            if (healthcheck != null) {
                cholesterollist.add(healthcheck.getCholesterol());
            }
        }
        for (Integer integer : cholesterollist) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/healthcheck/toxic_screen/general")
    public ResponseEntity<HashMap<Integer, Integer>> getGeneralToxicScreen() {
        HashMap<Integer, Integer> data = new HashMap<>();
        ArrayList<Integer> toxicscreenlist = new ArrayList<>();
        for (Inmate inmate : inmateService.getInmates()) {
            Healthcheck healthcheck = inmate.getLastHealthLog();
            if (healthcheck != null) {
                toxicscreenlist.add(healthcheck.getToxic_screen());
            }
        }
        for (Integer integer : toxicscreenlist) {
            data.putIfAbsent(integer, 0);
            data.put(integer, data.get(integer)+1);
        }
        return ResponseEntity.ok().body(data);
    }

    // @GetMapping("/{inmate_id}/shifts/{shift_id}")
    // public ResponseEntity<Workstation> getShiftInmate(  @PathVariable(value = "id") long inmate_id,
    //                                                     @PathVariable(value = "id") long shift_id) 
    //                                                     throws ResourceNotFoundException {
    //     Inmate inmate = inmateService.getInmateById(inmate_id);
    //     if (inmate != null) {
    //         for (Workstation shift : inmate.getShifts()) {
    //             if (shift.getId() == shift_id) {
    //                 return ResponseEntity.ok().body(shift);
    //             }
    //         }
    //     } 
    //     return ResponseEntity.ok().body(null);
    // }
}
