package ies.grupo51.lockedin.services;

import org.springframework.beans.factory.annotation.Autowired;

import ies.grupo51.lockedin.models.ERole;
import ies.grupo51.lockedin.models.Role;
import ies.grupo51.lockedin.repositories.RoleRepository;

public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role findRole(ERole roleUser) {
        return repository.findByName(roleUser);
    }

    public String findAll() {
        return repository.findAll().toString();
    }
}
