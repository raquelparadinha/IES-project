package ies.grupo51.lockedin.models;

import java.text.DateFormat;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guard")
public class Guard {
    @Id
    private long id;

    private String name;
    private String email;
    private String phone;
    private DateFormat birth_date;
    private Set<Area> areas;

    private static long counter = 100;

    public Guard () {
        this.id = Guard.counter++;
    }

    public Guard (String name, String email, String phone, DateFormat birth_date, Set<Area> areas) {
        this.id = Guard.counter++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.areas = areas;
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
    public void setBirth_date(DateFormat birth_date) {
        this.birth_date = birth_date;
    }
    public void setAreas(Set<Area> areas) {
        this.areas = areas;
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
    public DateFormat getBirth_date() {
        return birth_date;
    }
    public Set<Area> getAreas() {
        return areas;
    }

    @Override
    public String toString() {
        String result = String.format(
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %s, Birth date: %s, Areas: %s]", 
            id, name, email, phone, birth_date.toString(), areas);
        return result;
    }
}
