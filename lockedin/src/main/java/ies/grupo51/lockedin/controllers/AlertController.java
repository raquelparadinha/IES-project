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
import ies.grupo51.lockedin.models.Alert;
import ies.grupo51.lockedin.models.HealthAlert;
import ies.grupo51.lockedin.models.HealthLog;
import ies.grupo51.lockedin.models.WorkAlert;
import ies.grupo51.lockedin.models.WorkLog;
import ies.grupo51.lockedin.services.AlertService;
import ies.grupo51.lockedin.services.HealthLogService;
import ies.grupo51.lockedin.services.WorkLogService;

@CrossOrigin
@RestController
@RequestMapping("/api/alert")
public class AlertController {

    // SERVICES
    
    @Autowired
    public AlertService alertService;

    @Autowired
    public HealthLogService healthLogService;

    @Autowired
    public WorkLogService workLogService;

    // GET METHODS

    @GetMapping("")
    public ResponseEntity<List<HashMap<String, Object>>> getAllAlerts() throws ResourceNotFoundException{
        List<HashMap<String, Object>> data = new ArrayList<>();
        List<Alert> alerts = alertService.getAlerts();
        for (Alert alert : alerts) {
            alert.setSeen(true);
            alertService.updateAlert(alert);
        }
        alerts.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (alerts.size() > 30) {
            alerts = alerts.subList(0, 30);
        }
        for (Alert alert : alerts ) {
            if (alert.getType().equals("health")) {
                HealthAlert healthAlert = (HealthAlert) alert;
                HealthLog healthLog = healthLogService.getHealthLogById(healthAlert.getHealthLogId());
                HashMap<String, Object> insert = new HashMap<>();
                insert.put("Alert", alert);
                insert.put("InmateID", healthLog.getInmateId());
                data.add(insert);
            } else if (alert.getType().equals("work")) {
                WorkAlert workAlert = (WorkAlert) alert;
                WorkLog workLog = workLogService.getWorkLogById(workAlert.getWorkLogId());
                HashMap<String, Object> insert = new HashMap<>();
                insert.put("Alert", alert);
                insert.put("InmateID", workLog.getInmateId());
                data.add(insert);
            } else if (alert.getType().equals("riot")) {
                HashMap<String, Object> insert = new HashMap<>();
                insert.put("Alert", alert);
                data.add(insert);
            }
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable(value = "id") long id) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(alertService.getAlertById(id));
    }

    @GetMapping("/new")
    public ResponseEntity<List<Alert>> getAllNewAlerts() throws ResourceNotFoundException {
        List<Alert> alerts = alertService.getAlerts();
        alerts.sort((alert1, alert2) -> -alert1.getTimestamp().compareTo(alert2.getTimestamp()));
        if (alerts.size() > 5) {
            alerts = alerts.subList(0, 5);
        }
        List<Alert> data = new ArrayList<>();
        for (Alert alert : alerts) {
            if (alert.getSeen() == false) {
                data.add(alert);
                alert.setSeen(true);
                alertService.updateAlert(alert);
            }
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
