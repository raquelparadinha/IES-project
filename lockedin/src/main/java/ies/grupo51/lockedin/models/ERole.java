package ies.grupo51.lockedin.models;

public enum ERole {
    ROLE_USER,
    ROLE_ADMIN;

    public String getName() {
        switch(this) {
          case ROLE_USER:
            return "user";
    
          case ROLE_ADMIN:
            return "admin";
        }
        return null;
    }
}
