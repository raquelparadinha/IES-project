package ies.grupo51.lockedin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.services.GuardService;
import ies.grupo51.lockedin.services.WardenService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    public WardenService wardenService;

    @Autowired
    public GuardService guardService;
}
