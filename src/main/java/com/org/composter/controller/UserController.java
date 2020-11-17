package com.org.composter.controller;

import com.org.composter.request.LoginRequest;
import com.org.composter.request.RegisterRequest;
import com.org.composter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * controller method for processing login reques.
     * @param request login request body
     * @return user choice on success or nouser on failure.
     */
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> login(@RequestBody LoginRequest request) {
        LOG.info("start of processing of login request");
        boolean response = userService.login(request);
        return response?ResponseEntity.ok(request.getChoice()):ResponseEntity.ok("nouser");
    }

    @PostMapping(value = "/buyer", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> buyer(@RequestBody RegisterRequest request) {
        LOG.info("start of processing of buyer registration");
        boolean response = userService.addBuyer(request);
        return response?ResponseEntity.ok("successfull"):ResponseEntity.ok("exists");
    }

    @PostMapping(value = "/seller", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> seller(@RequestBody RegisterRequest request) {
        LOG.info("start of processing of seller registration");
        boolean response = userService.addSeller(request);
        return response?ResponseEntity.ok("successfull"):ResponseEntity.ok("exists");
    }

    @GetMapping("/seller/{id}")
    ResponseEntity<String> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.findSellerById(id));
    }
}
