package com.devmnack.springsecjwt.controller;

import com.devmnack.springsecjwt.model.AuthenticationRequest;
import com.devmnack.springsecjwt.model.AuthenticationResponse;
import com.devmnack.springsecjwt.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {

        logger.info("Executing helloWorld method");
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> completeAuthentication(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        logger.info("Executing completeAuthentication method");
        AuthenticationResponse response = authenticationService.performAuthentication(authenticationRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
