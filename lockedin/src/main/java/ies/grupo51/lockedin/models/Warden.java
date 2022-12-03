package ies.grupo51.lockedin.models;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("warden")
public class Warden implements Staff {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String phone;
    private DateFormat birth_date;
    private List<Message> messages;

    private static long counter = 1;

    public Warden() {
        this.id = UUID.randomUUID();
        this.messages = new ArrayList<>();
    }

    public Warden(String name, String email, String phone, DateFormat birth_date) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.messages = new ArrayList<>();
    }

    // SETS
    
    @Override
    public void setId(UUID id){
        this.id = id;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public void setBirth_date(DateFormat birth_date) {
        this.birth_date = birth_date;
    }
    @Override
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public static void setCounter(long counter) {
        Warden.counter = counter;
    }

    // GETS

    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getPhone() {
        return phone;
    }
    @Override
    public DateFormat getBirth_date() {
        return birth_date;
    }
    @Override
    public List<Message> getMessages() {
        return messages;
    }
    public static long getCounter() {
        return counter;
    }

    // CUSTOM

    @Override
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public String toString() {
        return String.format(
            "Warden [ID: %s, Name: %s, Email: %s, Phone: %s, Birth date: %s]", 
            this.id.toString(), this.name, this.email, this.phone, this.birth_date);
    }
}
