package ies.grupo51.lockedin.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Message")
public class Message {
    
    @Id
    private UUID id;

    private String type;
    private UUID reciever_id;
    private UUID sender_id;
    private String content;

    private static long counter = 1000;

    public Message() {
        this.id = UUID.randomUUID();
    }
    
    public Message(String type, UUID reciever_id, UUID sender_id, String content) {
        this.type = type;
        this.reciever_id = reciever_id;
        this.sender_id = sender_id;
        this.content = content;
    }

    // SETS

    public void setContent(String content) {
        this.content = content;
    }
    public static void setCounter(long counter) {
        Message.counter = counter;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setReciever_id(UUID reciever_id) {
        this.reciever_id = reciever_id;
    }
    public void setSender_id(UUID sender_id) {
        this.sender_id = sender_id;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    // GETS 

    public String getContent() {
        return content;
    }
    public static long getCounter() {
        return counter;
    }
    public UUID getId() {
        return id;
    }
    public UUID getReciever_id() {
        return reciever_id;
    }
    public UUID getSender_id() {
        return sender_id;
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return String.format(
            "Message : [ID: %s, Type: %s, Receiver :%s, Sender: %s, Content: %s]", 
            this.id.toString(), this.type, this.reciever_id.toString(), this.sender_id.toString(), this.content);
    }
    
}
