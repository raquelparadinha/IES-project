package ies.grupo51.lockedin.comms;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.amqp.core.Queue;

public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private Queue queue;

}
