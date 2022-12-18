package ies.grupo51.lockedin.comms;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.models.MoveSensorLog;
import ies.grupo51.lockedin.models.WorkLog;
import ies.grupo51.lockedin.models.WorkStation;
import ies.grupo51.lockedin.models.Area;
import ies.grupo51.lockedin.models.WorkAlert;
import ies.grupo51.lockedin.models.RiotAlert;
import ies.grupo51.lockedin.models.HealthAlert;
import ies.grupo51.lockedin.models.HealthLog;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.services.AlertService;
import ies.grupo51.lockedin.services.AreaService;
import ies.grupo51.lockedin.services.WorkAlertService;
import ies.grupo51.lockedin.services.RiotAlertService;
import ies.grupo51.lockedin.services.HealthAlertService;
import ies.grupo51.lockedin.services.HealthLogService;
import ies.grupo51.lockedin.services.InmateService;
import ies.grupo51.lockedin.services.MoveSensorLogService;
import ies.grupo51.lockedin.services.MoveSensorService;
import ies.grupo51.lockedin.services.WorkLogService;
import ies.grupo51.lockedin.services.WorkStationService;

@Service
public class Receiver {

    @Autowired private AlertService alertService;
    @Autowired private WorkAlertService workAlertService;
    @Autowired private RiotAlertService riotAlertService;
    @Autowired private HealthAlertService healthAlertService;
    @Autowired private AreaService areaService;
    @Autowired private InmateService inmateService;
    @Autowired private MoveSensorService moveSensorService;
    @Autowired private MoveSensorLogService moveSensorLogService;
    @Autowired private WorkStationService workStationService;
    @Autowired private WorkLogService workLogService;
    @Autowired private HealthLogService healthLogService;

    @RabbitListener(queues = CommsConfig.RECV_QUEUE)
    public void listen(String receivedmsg) throws ResourceNotFoundException {
        System.out.println("Received from datagen: " + receivedmsg);

        JSONObject jmsg = new JSONObject(receivedmsg);

        String type = jmsg.getString("type");

        Inmate inmate;
        long logId;
        
        switch(type) {
            case "sensor":
                // Get info
                inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                logId = moveSensorLogService.getNextId();
                // MoveSensorLogs

                MoveSensor moveSensor = moveSensorService.getMoveSensorById(jmsg.getInt("sensorid"));
                MoveSensorLog moveSensorLog = new MoveSensorLog(logId, inmate.getId(), moveSensor.getId());

                moveSensorLogService.saveMoveSensorLog(moveSensorLog);
                // MoveSensor
                moveSensor.addMoveLogIds(logId);
                moveSensorService.updatMoveSensor(moveSensor);
                // Exit Area
                Area exitArea = areaService.getAreaById(moveSensor.getExitAreaId());
                exitArea.getCurrentInmateIds().remove(inmate.getId());
                areaService.updateArea(exitArea);
                // Inmate
                inmate.addMoveLogId(logId);
                inmate.setAreaId(moveSensor.getExitAreaId());
                inmateService.updateInmate(inmate);
                // Entry Area
                Area entryArea = areaService.getAreaById(moveSensor.getExitAreaId());
                if (!(entryArea.getCurrentInmateIds().contains(inmate.getId()))) {
                    entryArea.getCurrentInmateIds().add(inmate.getId());
                }
                areaService.updateArea(entryArea);
                break;

            case "riot":
                // Trigger riot alert
                Area area = areaService.getAreaById(jmsg.getInt("areaid"));
                for (long inmateId : area.getCurrentInmateIds()) {
                    Inmate inmateInRiot = inmateService.getInmateById(inmateId);
                    inmateInRiot.setDanger(inmateInRiot.getDanger()+1);
                    inmateService.updateInmate(inmateInRiot);
                }
                RiotAlert riotAlert = new RiotAlert(
                    alertService.getNextId(),
                    "riot",
                    ("There's a riot at "+area.getName()),
                    area.getId(),
                    area.getCurrentInmateIds());
                    riotAlertService.saveRiotAlert(riotAlert);
                break;

            case "work":
                // Get Info
                inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                logId = workLogService.getNextId();
                // WorkLog
                WorkStation workStation = workStationService.getWorkStationById(jmsg.getInt("workstationid"));
                WorkLog workLog = new WorkLog(logId, inmate.getId(), workStation.getId(), jmsg.getInt("workquota"));
                workLogService.saveWorkLog(workLog);
                // Inmate
                inmate.addWorkLogId(logId); // adicionar um move aos move logs
                inmateService.updateInmate(inmate); // guardar na base de dados
                // WorkStation
                workStation.addWorkLogId(logId);
                workStationService.updatWorkStation(workStation);
                // Trigger work alert
                if (workStation.getExpectedQuota() > workLog.getQuota()) {
                    WorkAlert workAlert = new WorkAlert(alertService.getNextId(), 
                    "work", 
                    "Inmate "+inmate.getName()+" is not doing an expected job",
                    logId);
                    workAlertService.saveWorkAlert(workAlert);
                }
                break;
            
            case "healthcheck":
                // Get Info
                inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                logId = healthLogService.getNextId();
                // HealthLog
                JSONObject healthcheck = jmsg.getJSONObject("healthcheck");
                HealthLog healthLog = new HealthLog(logId, inmate.getId(), healthcheck);
                healthLogService.saveHealthLog(healthLog);
                // Inmate
                inmate.setHealthLogId(logId); // adicionar um move aos move logs
                inmateService.updateInmate(inmate); // guardar na base de dados
                // Trigger health alert
                HealthAlert healthAlert = new HealthAlert(alertService.getNextId(), "health", "Inmmate "+inmate.getName()+" is not feeling well");
                List<String> symptoms = new ArrayList<>();
                // HEART BEAT
                if (healthLog.getHeartBeat() > 130) {
                    symptoms.add("Elevated Heart Beat");
                } else if (healthLog.getHeartBeat() < 30) {
                    symptoms.add("Low Heart Beat");
                }
                // STRESS
                if (healthLog.getStress() > 90) {
                    symptoms.add("Elevated Stress");
                }
                // GLICOSE
                if (healthLog.getGlicose() > 130) {
                    symptoms.add("Elevated Glicose");
                }
                // URIC
                if (healthLog.getUricAcid() > 7.2) {
                    symptoms.add("Elevated Uric Acid");
                } else if (healthLog.getUricAcid() < 2.8) {
                    symptoms.add("Low Uric Acid");
                }
                // CHOLESTEROL
                if (healthLog.getCholesterol() > 200) {
                    symptoms.add("Elevated Cholesterol");
                } else if (healthLog.getCholesterol() < 100) {
                    symptoms.add("Low Cholesterol");
                }
                // TOXIC SCREEN
                if (healthLog.getToxicScreen() == 2) {
                    symptoms.add("Major Symptoms of Drug Use");
                } else if (healthLog.getToxicScreen() == 1) {
                    symptoms.add("Minor Symptoms of Drug Use");
                }

                if (symptoms.size() != 0) {
                    healthAlert.setSymptoms(symptoms);
                    healthAlert.setHealthLogId(logId);
                    healthAlertService.saveHealthAlert(healthAlert);
                }

                break;
                
            default:
                System.err.println("Couldn't read message type.");
                break;
        }
        System.out.println("Succesfully handled message.");
    }
}