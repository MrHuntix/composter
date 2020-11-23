package com.org.composter.controller;

import com.org.composter.request.LoginRequest;
import com.org.composter.request.RegisterRequest;
import com.org.composter.response.SimpleResponse;
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
    ResponseEntity<SimpleResponse> login(@RequestBody LoginRequest request) {
        LOG.info("start of processing of login request");
        boolean response = userService.login(request);
        return response?ResponseEntity.ok(new SimpleResponse(request.getChoice())):ResponseEntity.ok(new SimpleResponse("nouser"));
    }

    @PostMapping(value = "/buyer", consumes = "application/json", produces = "application/json")
    ResponseEntity<SimpleResponse> buyer(@RequestBody RegisterRequest request) {
        LOG.info("start of processing of buyer registration");
        boolean response = userService.addBuyer(request);
        return response?ResponseEntity.ok(new SimpleResponse("successfull")):ResponseEntity.ok(new SimpleResponse("exists"));
    }

    @PostMapping(value = "/seller", consumes = "application/json", produces = "application/json")
    ResponseEntity<SimpleResponse> seller(@RequestBody RegisterRequest request) {
        LOG.info("start of processing of seller registration");
        boolean response = userService.addSeller(request);
        return response?ResponseEntity.ok(new SimpleResponse("successfull")):ResponseEntity.ok(new SimpleResponse("exists"));
    }

    @GetMapping(value = "/seller/{id}")
    ResponseEntity<SimpleResponse> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(new SimpleResponse(userService.findSellerById(id)));
    }
}
