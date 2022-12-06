package ies.grupo51.lockedin.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guard")
public class Guard {

    @Id
    private long id;

    private String name;
    private String email;
    private String phone;
    private Date birthdate;
    private long areaId;
    private String password;

    private static long counter = 100;

    public Guard () {
        this.id = 0;
    }

    public Guard (long id, String name, String email, String phone, Date birthdate, long areaId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.areaId = areaId;
        this.password = password;
    }

    // SETS

    public void setId(long id){
        this.id = id;
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
    public void setBirthdate(Date birth_date) {
        this.birthdate = birth_date;
    }
    public String getPassword() {
        return password;
    }
    public long getAreaId() {
        return areaId;
    }
    public static void setCounter(long counter) {
        Guard.counter = counter;
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
    public Date getBirthdate() {
        return birthdate;
    }
    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        String result = String.format(
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %s, Birth date: %s]", 
            this.id, this.name, this.email, this.phone, this.birthdate.toString());
        return result;
    }
}
