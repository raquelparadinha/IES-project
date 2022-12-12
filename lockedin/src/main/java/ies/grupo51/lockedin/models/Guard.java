package ies.grupo51.lockedin.models;

import java.util.ArrayList;
// import java.util.HashSet;
import java.util.List;
// import java.util.Set;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
// import org.springframework.data.moncgodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import ies.grupo51.lockedin.services.RoleService;

@Document("guard")
public class Guard {

    @Id
    private long id;

    private String name;
    // @NotBlank
    // @Email
    private String email;
    private String phone;
    private String birthdate;
    private long areaId;
    // @NotBlank
    private String password;

    // @DBRef
    private List<Role> roles = new ArrayList<>();

    private static long counter = 100;
    
    @Autowired
    private RoleService service;

    public Guard () {
        this.id = 0;
        Role userRole = service.findRole(ERole.ROLE_USER);
		this.roles.add(userRole);
    }

    public Guard (long id, String name, String email, String phone, String birthdate, long areaId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.areaId = areaId;
        this.password = password;
        Role userRole = service.findRole(ERole.ROLE_USER);
		this.roles.add(userRole);
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
    public void setBirthdate(String birth_date) {
        this.birthdate = birth_date;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
    public String getPhone() {
        return phone;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public List<Role> getRoles() {
        return roles;
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
            "Guard [ID: %d, Name: %s, Email: %s, Phone: %s, Birth date: %s]", 
            this.id, this.name, this.email, this.phone, this.birthdate.toString());
        return result;
    }
}
