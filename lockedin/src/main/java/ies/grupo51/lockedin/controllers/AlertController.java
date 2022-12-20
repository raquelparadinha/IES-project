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
import ies.grupo51.lockedin.models.Alert;
import ies.grupo51.lockedin.services.AlertService;

@CrossOrigin
@RestController
@RequestMapping("/api/alert")
public class AlertController {

    // SERVICES
    
    @Autowired
    public AlertService alertService;

    // GET METHODS

    @GetMapping("")
    public ResponseEntity<List<Alert>> getAllAlerts() throws ResourceNotFoundException{
        List<Alert> data = alertService.getAlerts();
        for (Alert alert : data) {
            alert.setSeen(true);
            alertService.updateAlert(alert);
        }
        data.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (data.size() > 30) {
            data = data.subList(0, 30);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAllAlerts(@PathVariable(value = "id") long id) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(alertService.getAlertById(id));
    }

    @GetMapping("/new")
    public ResponseEntity<List<Alert>> getAllNewAlerts() throws ResourceNotFoundException {
        List<Alert> data = new ArrayList<>();
        for (Alert alert : alertService.getAlerts()) {
            if (alert.getSeen() == false) {
                data.add(alert);
            }
        }
        data.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (data.size() > 5) {
            data = data.subList(0, 5);
        }
        for (Alert alert : data) {
            alert.setSeen(true);
            alertService.updateAlert(alert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/riot")
    public ResponseEntity<List<Alert>> getRiotAlerts(){
        List<Alert> alerts = alertService.getAlertByType("riot");
        alerts.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        
        if (alerts.size() > 30) {
            alerts = alerts.subList(0, 30);
        }
        return ResponseEntity.ok().body(alerts);
    }

    @GetMapping("/work")
    public ResponseEntity<List<Alert>> getWorkAlerts(){
        List<Alert> alerts = alertService.getAlertByType("work");
        alerts.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (alerts.size() > 30) {
            alerts = alerts.subList(0, 30);
        }
        return ResponseEntity.ok().body(alerts);
    }

    @GetMapping("/health")
    public ResponseEntity<List<Alert>> getHealthAlerts(){
        List<Alert> alerts = alertService.getAlertByType("health");
        alerts.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        
        if (alerts.size() > 30) {
            alerts = alerts.subList(0, 30);
        }
        return ResponseEntity.ok().body(alerts);
    }

}
