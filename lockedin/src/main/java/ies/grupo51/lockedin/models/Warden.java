package ies.grupo51.lockedin.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("warden")
public class Warden extends User {

    @Id
    private long id;

    private String name;
    @NotBlank
    @Email
    private String email;
    private String phone;
    private Date birthdate;
    @NotBlank
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private static long counter = 0;

    public Warden() {
        this.id = 0;
    }

    public Warden(long id, String name, String email, String phone, Date birthdate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.password = password;
    }

    // SETS
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public static void setCounter(long counter) {
        Warden.counter = counter;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // GETS

    public Date getBirthdate() {
        return birthdate;
    }
    public static long getCounter() {
        return counter;
    }
    public String getEmail() {
        return email;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return String.format(
            "Warden [ID: %d, Name: %s, Email: %s, Phone: %s, Birth Date: %s]", 
            this.id, this.name, this.email, this.phone, this.birthdate.toString());
    }
}
