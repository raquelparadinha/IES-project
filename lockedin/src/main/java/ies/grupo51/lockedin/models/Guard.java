package ies.grupo51.lockedin.models;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guard")
public class Guard {
    @Id
    private long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate birth_date;
    private ArrayList<Area> areas;
    private static long counter = 100;
    // private String password;

    public Guard () {
        this.id = Guard.counter++;
    }

    public Guard (String name, String email, String phone, LocalDate birth_date, ArrayList<Area> areas) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth_date = birth_date;
        this.areas = areas;
        this.id = Guard.counter++;
    }

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
    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public long getid() {
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
    public ArrayList<Area> getAreas() {
        return areas;
    }

    @Override
    public String toString() {
        String result = String.format(
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %s, Birth date: %s, Areas: ", 
            id, name, email, phone, birth_date);

        for (Area a : areas) {
            result += String.format("%s, ", a.getName());
        }
        result += "]";

        return result;
    }
}
