package com.org.composter.controller;

import com.org.composter.response.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {

    @GetMapping("/ping")
    private ResponseEntity<SimpleResponse> ping() {
        return ResponseEntity.ok(new SimpleResponse("service is up and running"));
    }
}
