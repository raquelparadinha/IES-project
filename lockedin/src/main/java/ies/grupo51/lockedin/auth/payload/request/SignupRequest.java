package ies.grupo51.lockedin.auth.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest { 
    @NotBlank
    @Email
    private String email;
    
    private Set<String> roles;
    
    @NotBlank
    private String password;
  
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRole(Set<String> roles) {
      this.roles = roles;
    }
}
