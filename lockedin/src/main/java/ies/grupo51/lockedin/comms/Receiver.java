package ies.grupo51.lockedin.comms;

import org.json.JSONObject;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.models.MoveSensorLog;
import ies.grupo51.lockedin.models.WorkLog;
import ies.grupo51.lockedin.models.HealthLog;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.services.HealthLogService;
import ies.grupo51.lockedin.services.InmateService;
import ies.grupo51.lockedin.services.MoveSensorLogService;
import ies.grupo51.lockedin.services.MoveSensorService;
import ies.grupo51.lockedin.services.WorkLogService;

@Service
public class Receiver {

    @Autowired private InmateService inmateService;
    @Autowired private MoveSensorService moveSensorService;

    @Autowired private MoveSensorLogService moveSensorLogService;
    @Autowired private WorkLogService workLogService;
    @Autowired private HealthLogService healthLogService;

    @Autowired private Sender sender;

    @RabbitListener(queues = CommsConfig.RECV_QUEUE)
    public void listen(String receivedmsg) throws ResourceNotFoundException {
        System.out.println("Received from datagen: " + receivedmsg);

        JSONObject jmsg = new JSONObject(receivedmsg);
        String type = jmsg.getString("type");

        Inmate inmate;
        long id;
        
        switch(type) {
            case "sensor":
                inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                MoveSensor moveSensor = moveSensorService.getMoveSensorById(jmsg.getInt("sensorid"));
                id = moveSensorLogService.getRepositorySize();
                moveSensorLogService.saveMoveSensorLog(new MoveSensorLog(id, inmate.getId(), moveSensor.getId()));
                break;

            case "riot":
                // trigger riot alert
                break;

            case "work":
                inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                id = workLogService.getRepositorySize();
                int workstationid = jmsg.getInt("workstationid");
                int workquota = jmsg.getInt("workquota");
                workLogService.saveWorkLog(new WorkLog(id, inmate.getId(), workstationid, workquota));
                // trigger work alert
                break;
            
            case "healthcheck":
                inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                id = healthLogService.getRepositorySize();
                JSONObject healthcheck = jmsg.getJSONObject("healthcheck");
                healthLogService.saveHealthLog(new HealthLog(id, inmate.getId(), healthcheck));
                // trigger health alert
                break;
                
            default:
                System.err.println("Couldn't read message type.");
                break;
        }
    }
}