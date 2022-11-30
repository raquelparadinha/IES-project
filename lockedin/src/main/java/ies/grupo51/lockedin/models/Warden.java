package ies.grupo51.lockedin.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("warden")
public class Warden {

    @Id
    private long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate birth_date;

    private static long counter = 1;

    public Warden() {
        this.id = Warden.counter++;
    }

    public Warden(String name, String email, String phone, LocalDate birth_date) {
        this.id = Warden.counter++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
    }

    // SETS

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    // GETS

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public LocalDate getBirth_date() {
        return birth_date;
    }

    @Override
    public String toString() {
        return String.format(
            "Warden [ID: %d, Name: %s, Email: %s, Phone: %s, Birth date: %s]", 
            id, name, email, phone, birth_date);
    }
}
