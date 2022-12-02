package ies.grupo51.lockedin.controllers;

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
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.models.Workstation;
import ies.grupo51.lockedin.services.InmateService;

@CrossOrigin
@RestController
@RequestMapping("/api/staff")
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
    public ResponseEntity<Inmate> getInmateById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
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
}
