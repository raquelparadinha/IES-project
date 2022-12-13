package ies.grupo51.lockedin.comms;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.MoveSensor;
import ies.grupo51.lockedin.models.MoveSensorLog;
import ies.grupo51.lockedin.models.Inmate;
import ies.grupo51.lockedin.services.InmateService;
import ies.grupo51.lockedin.services.MoveSensorLogService;
import ies.grupo51.lockedin.services.MoveSensorService;

@Component
public class Receiver {

    @Autowired private InmateService inmateService;
    @Autowired private MoveSensorService moveSensorService;
    @Autowired private MoveSensorLogService moveSensorLogService;

    @RabbitListener(queues = CommsConfig.RECV_QUEUE)
    public void listen(String receivedmsg) throws ResourceNotFoundException {
        System.out.print("Received from datagen: " + receivedmsg);

        JSONObject jmsg = new JSONObject(receivedmsg);
        String type = jmsg.getString("type");
        
        switch(type) {
            case "sensor":
                Inmate inmate = inmateService.getInmateById(jmsg.getInt("inmateid"));
                MoveSensor moveSensor = moveSensorService.getMoveSensorById(jmsg.getInt("sensorid"));
                moveSensorLogService.saveMoveSensorLog(new MoveSensorLog(inmate.getId(), moveSensor.getId()));
                break;
            case "riot":
                break;
            case "work":
                break;
            case "healthcheck":
                break;
            default:
                System.err.println("Couldn't read message type.");
                break;
        }

    }
}