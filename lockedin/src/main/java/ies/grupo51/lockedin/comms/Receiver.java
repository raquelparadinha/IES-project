package ies.grupo51.lockedin.comms;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import ies.grupo51.lockedin.services.MoveSensorDataService;

public class Receiver {

    @Autowired
    private MoveSensorDataService moveSensorDataService;

    @RabbitListener(queues = CommsConfig.QUEUE)
    public void listen(String receivedmsg) {
        System.out.print("Received from datagen: " + receivedmsg);

        try {
            JSONObject jmsg = new JSONObject(receivedmsg);
            String type = jmsg.getString("type");
            JSONObject args = new JSONObject(jmsg.getString("args"));

            switch("type") {
                case "sensor":
                    long inmateid = jmsg.getInt("inmateid");
                    long sensorid = jmsg.getInt("sensorid");
                    break;
                case "apply":
                    break;
                case "work":
                    break;
                case "healthcheck":
                    break;
                default:
                    System.err.println("Couldn't read message type.");
                    break;
            }
        } catch (JSONException je) {
            System.err.println("Couldn't decode JSON. Exiting.");
            System.exit(1);
        }
    }
}