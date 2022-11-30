package ies.grupo51.lockedin.comms;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public void recv(String msg) {
        System.out.print(msg);
    }
}