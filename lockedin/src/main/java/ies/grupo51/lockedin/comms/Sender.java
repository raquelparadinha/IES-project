package ies.grupo51.lockedin.comms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private Queue send_queue;

    public void send() {
        //rabbitTemplate()
    }

}