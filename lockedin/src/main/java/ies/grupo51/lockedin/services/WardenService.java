package ies.grupo51.lockedin.services;

import org.springframework.beans.factory.annotation.Autowired;

import ies.grupo51.lockedin.repositories.WardenRepository;

public class WardenService {
    @Autowired
    private WardenRepository repository;
}
