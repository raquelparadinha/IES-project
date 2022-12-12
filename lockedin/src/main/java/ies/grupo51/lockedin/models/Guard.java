package ies.grupo51.lockedin.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guard")
public class Guard {

    @Id
    private long id;

    private String name;
    @NotBlank
    @Email
    private String email;
    private long phone;
    private String birthdate;
    private long areaId;
    @NotBlank
    private String password;

    private static long counter = 100;
    
    public Guard () {
        this.id = 0;
    }

    public Guard (long id, String name, String email, long phone, String birthdate, long areaId, String password) {
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
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public void setBirthdate(String birth_date) {
        this.birthdate = birth_date;
    }
    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public long getPhone() {
        return phone;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public String getPassword() {
        return password;
    }
    public long getAreaId() {
        return areaId;
    }    
    public static long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        String result = String.format(
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %d, Birth date: %s]", 
            this.id, this.name, this.email, this.phone, this.birthdate.toString());
        return result;
    }
}
