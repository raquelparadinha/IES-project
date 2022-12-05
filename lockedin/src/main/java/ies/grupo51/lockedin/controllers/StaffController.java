package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.models.Message;
import ies.grupo51.lockedin.models.Staff;
import ies.grupo51.lockedin.models.Warden;
import ies.grupo51.lockedin.models.Workstation;
import ies.grupo51.lockedin.services.GuardService;
import ies.grupo51.lockedin.services.WardenService;

@CrossOrigin
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    // SERVICES

    @Autowired 
    private GuardService guardService;
    
    @Autowired 
    private WardenService wardenService;

    // GET METHODS

    @GetMapping("/")
    public ResponseEntity<List<Staff>> getStaff() {
        List<Staff> data = new ArrayList<>();
        for (Warden warden : wardenService.getWardens()) {
            data.add(warden);
        }
        for (Guard guard : guardService.getGuards()) {
            data.add(guard);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffMember(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Staff data = wardenService.getWardenById(id);
        if (data == null) {
            data = guardService.getGuardById(id);
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/{id}/shifts")
    public ResponseEntity<Set<Workstation>> getStaffMemberShifts(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Set<Workstation> data = new HashSet<>();
        Guard guard = guardService.getGuardById(id);
        if (guard != null) {
            data = guard.getShifts();
        }
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/{id}/messages")
    public ResponseEntity<List<Message>> getStaffMemberMessages(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        List<Message> data = new ArrayList<>();
        Guard guard = guardService.getGuardById(id);
        if (guard != null) {
            data = guard.getMessages();
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{staff_id}/shifts/{shift_id}")
    public ResponseEntity<Workstation> getShiftStaff(   @PathVariable(value = "staff_id") UUID staff_id, 
                                                        @PathVariable(value = "shift_id") UUID shift_id) 
                                                        throws ResourceNotFoundException {
        Guard guard = guardService.getGuardById(staff_id);
        if (guard != null) {
            for (Workstation shift : guard.getShifts()) {
                if (shift.getId() == shift_id) {
                    return ResponseEntity.ok().body(shift);
                }
            }
        }
        return ResponseEntity.ok().body(null);
    }
    @GetMapping("/{staff_id}/messages/{message_id}")
    public ResponseEntity<Message> getMessage(  @PathVariable(value = "staff_id") UUID staff_id, 
                                                @PathVariable(value = "messages_id") UUID message_id) 
                                                throws ResourceNotFoundException {
        Warden warden = wardenService.getWardenById(staff_id);
        if (warden != null) {
            for (Message message : warden.getMessages()) {
                if (message.getId() == message_id) {
                    return ResponseEntity.ok().body(message);
                }
            }
        }
        Guard guard = guardService.getGuardById(staff_id);
        if (guard != null) {
            for (Message message : guard.getMessages()) {
                if (message.getId() == message_id) {
                    return ResponseEntity.ok().body(message);
                }
            }
        }
        return ResponseEntity.ok().body(null);
    }
}
