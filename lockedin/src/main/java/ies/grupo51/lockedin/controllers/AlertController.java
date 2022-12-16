package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Alert>> getAllAlerts(){
        List<Alert> alerts = alertService.getAlerts();
        alerts.sort((alert1, alert2) -> alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (alerts.size() > 30) {
            alerts = alerts.subList(0, 30);
        }
        return ResponseEntity.ok().body(alerts);
    }

    @GetMapping("/new")
    public ResponseEntity<List<Alert>> getAllNewAlerts() {
        List<Alert> data = new ArrayList<>();
        for (Alert alert : alertService.getAlerts()) {
            if (alert.getSeen() == false) {
                data.add(alert);
            }
        }
        data.sort((alert1, alert2) -> alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (data.size() > 5) {
            data = data.subList(0, 5);
        }
        return ResponseEntity.ok().body(data);
    }

}
