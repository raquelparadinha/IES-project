package ies.grupo51.lockedin.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    @GetMapping("/{id}")
    public ResponseEntity<WorkStation> getWorkStationById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(workStationService.getWorkStationById(id));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<HashMap<String, Object>> getWorkstationDetails(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        HashMap<String, Object> data = new HashMap<>();
        WorkStation workStation = workStationService.getWorkStationById(id);
        data.put("expectedQuota", workStation.getExpectedQuota());
        data.put("numWorkLogs", workStation.getWorkLogIds().size());

        int averageQuota = 0;
        HashMap<Inmate, List<WorkLog>> inmateWorks = new HashMap<>();

        Inmate bestWorker = null, worstWoker = null;
        float bestAvgQuota = 0.0f, worstAvgQuota = 100.0f;
        for (long workLogId : workStation.getWorkLogIds()) {
            WorkLog workLog = workLogService.getWorkLogById(workLogId);
            System.out.println(workLog.getId() + " - " + workLog.getInmateId() + " - " + workLog.getQuota());
            // averageQuota
            averageQuota += workLog.getQuota();

            // inmateWorks
            Inmate inmate = inmateService.getInmateById(workLog.getInmateId());
            inmateWorks.putIfAbsent(inmate, new ArrayList<>());
            List<WorkLog> works = inmateWorks.get(inmate);
            works.add(workLog);
            inmateWorks.put(inmate, works);
        }

        Map<Inmate, Float> avgQuotas = new HashMap<>();

        for (Inmate inmate : inmateWorks.keySet()) {
            int inmateQuotaSum = 0;
            for (WorkLog workLog : inmateWorks.get(inmate))
                inmateQuotaSum += workLog.getQuota();
            float inmateAvgQuota = (float) inmateQuotaSum / inmateWorks.get(inmate).size();
            avgQuotas.put(inmate, inmateAvgQuota);
            
            if (inmateAvgQuota > bestAvgQuota) {
                bestWorker = inmate;
                bestAvgQuota = inmateAvgQuota;
            } else if (inmateAvgQuota < worstAvgQuota) {
                worstWoker = inmate;
                worstAvgQuota = inmateAvgQuota;
            }
        }

        for (Inmate i : avgQuotas.keySet()) {
            System.out.println(i.getName() + " - " + avgQuotas.get(i));
        }

        // for (Inmate inmate : inmateWorks.keySet()) {
        //     int newInmateWorks = 0;
        //     for (WorkLog workLog : inmateWorks.get(inmate)) {
        //         newInmateWorks += workLog.getQuota();
        //     }
        //     newInmateWorks = newInmateWorks/inmateWorks.get(inmate).size();
        //     System.out.println("newinmate: + " + inmate.getName());
        //     System.out.println(newInmateWorks);

        //     if (bestWorker == null) {
        //         bestWorker = inmate;
        //     } else {
        //         int bestWorkerWorks = 0;
        //         for (WorkLog workLog : inmateWorks.get(bestWorker)) {
        //             bestWorkerWorks += workLog.getQuota();
        //         }
        //         bestWorkerWorks = bestWorkerWorks/inmateWorks.get(bestWorker).size();
        //         System.out.println(bestWorkerWorks);

        //         if (bestWorkerWorks <= newInmateWorks) {
        //             bestWorker = inmate;
        //         }
        //     }
        //     if (worstWoker == null) {
        //         worstWoker = inmate;
        //     } else {
        //         int worstWokerWorks = 0;
        //         for (WorkLog workLog : inmateWorks.get(worstWoker)) {
        //             worstWokerWorks += workLog.getQuota();
        //         }
        //         worstWokerWorks = worstWokerWorks/inmateWorks.get(worstWoker).size();
        //         System.out.println(worstWokerWorks);

        //         if (worstWokerWorks >= newInmateWorks) {
        //             worstWoker = inmate;
        //         }
        //     }
        // }

        System.out.println("workers");
        System.out.println(bestWorker);
        System.out.println(worstWoker);

        data.put("averageQuota", workStation.getWorkLogIds().size() != 0 ? averageQuota/workStation.getWorkLogIds().size() : 0);

        data.put("bestWorker", bestWorker != null ? bestWorker.getName() : "");
        data.put("worstWoker", worstWoker != null ? worstWoker.getName() : "");

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}/worklogs")
    public ResponseEntity<List<HashMap<String, Integer>>> getWorkStationWorkLogs(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        List<HashMap<String,Integer>> data = new ArrayList<>();
        List<WorkLog> workLogs = new ArrayList<>();
        for (long workLogId : workStationService.getWorkStationById(id).getWorkLogIds()) {
            WorkLog workLog = workLogService.getWorkLogById(workLogId);
            workLogs.add(workLog);
        }
        workLogs.sort((workLog1, workLog2) -> -workLog1.getTimestamp().compareTo(workLog2.getTimestamp()));
        if (workLogs.size() > 50) {
            workLogs = workLogs.subList(0, 50);
        }
        workLogs.sort((workLog1, workLog2) -> workLog1.getTimestamp().compareTo(workLog2.getTimestamp()));
        for (int i = 0; i < workLogs.size(); i++) {
            HashMap<String,Integer> insert = new HashMap<>();
            insert.put("x", i);
            insert.put("y", workLogs.get(i).getQuota());
            data.add(insert);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/worklog/{id}/inmate")
    public ResponseEntity<HashMap<String, Object>> getWorkLogInmateInfo(@PathVariable(value = "id") long id ) throws ResourceNotFoundException{
        HashMap<String, Object> data = new HashMap<>();
        data.put("workLogId", id);
        WorkLog workLog = workLogService.getWorkLogById(id);
        Inmate inmate = inmateService.getInmateById(workLog.getInmateId());
        data.put("inmateName", inmate.getName());
        return ResponseEntity.ok().body(data);
    }
}
