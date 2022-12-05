package ies.grupo51.lockedin.auth.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.auth.models.ERole;
import ies.grupo51.lockedin.auth.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}