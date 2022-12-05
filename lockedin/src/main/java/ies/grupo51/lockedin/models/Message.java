package ies.grupo51.lockedin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Message")
public class Message {
    
    @Id
    private long id;

    private String type;
    private long reciever_id;
    private long sender_id;
    private String content;

    private static long counter = 1000;

    public Message() {
        this.id = 0;
    }
    
    public Message(long id, String type, long reciever_id, long sender_id, String content) {
        this.id = id;
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
    public void setId(long id) {
        this.id = id;
    }
    public void setReciever_id(long reciever_id) {
        this.reciever_id = reciever_id;
    }
    public void setSender_id(long sender_id) {
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
    public long getId() {
        return id;
    }
    public long getReciever_id() {
        return reciever_id;
    }
    public long getSender_id() {
        return sender_id;
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return String.format(
            "Message : [ID: %d, Type: %s, Receiver :%d, Sender: %d, Content: %s]", 
            this.id, this.type, this.reciever_id, this.sender_id, this.content);
    }
    
}
