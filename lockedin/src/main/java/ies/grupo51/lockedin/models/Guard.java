package ies.grupo51.lockedin.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("guard")
public class Guard {

    @Id
    private long id;

    private String name;
    private String email;
    private long phone;
    private String birthdate;
    private long areaId;
    private String password;
    private List<String> roles;

    public Guard () {
        this.id = 0;
    }

    public Guard (long id, String name, String email, long phone, String birthdate, long areaId, String password, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.areaId = areaId;
        this.password = password;
        this.roles = roles;
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
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public List<String> getRoles() {
        return roles;
    }
    public String getPassword() {
        return password;
    }
    public long getAreaId() {
        return areaId;
    }    

    @Override
    public String toString() {
        String result = String.format(
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %d, Birth date: %s, Roles: %s]", 
            this.id, this.name, this.email, this.phone, this.birthdate.toString(), this.roles.toString());
        return result;
    }
}
