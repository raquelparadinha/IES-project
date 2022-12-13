package ies.grupo51.lockedin.comms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void lockSensor(long sensorid) {
        JSONObject jmsg = new JSONObject();
        jmsg.put("type", "lock");
        jmsg.put("sensorid", sensorid);

        send(jmsg);
    }

    public void unlockSensor(long sensorid) {
        JSONObject jmsg = new JSONObject();
        jmsg.put("type", "unlock");
        jmsg.put("sensorid", sensorid);

        send(jmsg);
    }

    public void newInmate(long inmateid, long areaid) {
        JSONObject jmsg = new JSONObject();
        jmsg.put("type", "newinmate");
        jmsg.put("inmateid", inmateid);
        jmsg.put("areaid", areaid);

        send(jmsg);
    }

    public void deleteInmate(long inmateid) {
        JSONObject jmsg = new JSONObject();
        jmsg.put("type", "delinmate");
        jmsg.put("inmateid", inmateid);

        send(jmsg);
    }

    private void send(JSONObject jmsg) {
        String msg = jmsg.toString();
        rabbitTemplate.convertAndSend(CommsConfig.SEND_EXCHANGE, CommsConfig.SEND_ROUTING_KEY, msg);
        System.out.println("sent" + msg);
    }
}