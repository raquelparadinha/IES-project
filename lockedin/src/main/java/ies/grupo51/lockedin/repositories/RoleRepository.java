package ies.grupo51.lockedin.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.models.ERole;
import ies.grupo51.lockedin.models.Role;

public interface RoleRepository extends MongoRepository <Role, String> {

    Role findByName(ERole roleUser);    
    
}
