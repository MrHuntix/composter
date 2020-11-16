package com.org.composter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {

    @GetMapping("/ping")
    private ResponseEntity<String> ping() {
        return ResponseEntity.ok("service is up and running");
    }
}
