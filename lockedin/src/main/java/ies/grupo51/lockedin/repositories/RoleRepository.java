package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ies.grupo51.lockedin.models.ERole;
import ies.grupo51.lockedin.models.Role;

public class RoleRepository extends MongoRepository <Role, String> {

    public Role findRole(ERole roleUser) {
        return null;
    }
    
}
