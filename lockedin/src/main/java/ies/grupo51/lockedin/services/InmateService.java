package ies.grupo51.lockedin.services;

import org.springframework.beans.factory.annotation.Autowired;

import ies.grupo51.lockedin.repositories.InmateRepository;

public class InmateService {
    @Autowired
    private InmateRepository repository;
}
