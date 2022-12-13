package ies.grupo51.lockedin.comms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        rabbitTemplate.convertAndSend(CommsConfig.SEND_EXCHANGE, CommsConfig.SEND_ROUTING_KEY, msg);
    }
}