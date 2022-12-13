package ies.grupo51.lockedin.comms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

@Configuration
public class CommsConfig {
    public static final String RECV_EXCHANGE = "datagen";
    public static final String RECV_QUEUE = "datagen";
    public static final String SEND_EXCHANGE = "backend";
    public static final String SEND_QUEUE = "backend";
    public static final String ROUTING_KEY = "";

    @Bean Queue recv_queue() {
        return new Queue(RECV_QUEUE, false);
    }

    @Bean Queue send_queue() {
        return new Queue(SEND_QUEUE, false);
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
