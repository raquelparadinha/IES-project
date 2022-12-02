package ies.grupo51.lockedin.models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guard")
public class Guard implements Staff {
    @Id
    private long id;

    private String name;
    private String email;
    private String phone;
    private DateFormat birth_date;
    private Set<Workstation> shifts;
    private List<Message> messages;

    private static long counter = 100;

    public Guard () {
        this.id = Guard.counter++;
    }

    public Guard (String name, String email, String phone, DateFormat birth_date) {
        this.id = Guard.counter++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.shifts = new HashSet<>();
        this.messages = new ArrayList<>();
    }

    public Guard (String name, String email, String phone, DateFormat birth_date, Set<Workstation> shifts) {
        this.id = Guard.counter++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.shifts = shifts;
        this.messages = new ArrayList<>();
    }

    // SETS

    @Override
    public void setId(long id){
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
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public void setBirth_date(DateFormat birth_date) {
        this.birth_date = birth_date;
    }
    public void setShifts(Set<Workstation> shifts) {
        this.shifts = shifts;
    }
    @Override
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // GETS

    @Override
    public long getId() {
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
    public Set<Workstation> getShifts() {
        return shifts;
    }
    @Override
    public List<Message> getMessages() {
        return messages;
    }

    // CUSTOM

    @Override
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void addShift(Workstation workstation) {
        this.shifts.add(workstation);
    }

    @Override
    public String toString() {
        String result = String.format(
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %s, Birth date: %s]", 
            this.id, this.name, this.email, this.phone, this.birth_date.toString());
        return result;
    }
}
