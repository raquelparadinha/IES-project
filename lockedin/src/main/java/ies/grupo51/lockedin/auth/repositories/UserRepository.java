package ies.grupo51.lockedin.auth.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ies.grupo51.lockedin.auth.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}