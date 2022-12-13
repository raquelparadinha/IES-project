package ies.grupo51.lockedin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ies.grupo51.lockedin.models.Inmate;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("counter")
    public  ResponseEntity<Long> getCounter() {

        return ResponseEntity.ok().body(Inmate.getCounter());
    }
}
