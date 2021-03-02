package com.example.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.AuthenticateDTO;
import com.example.auth.dto.InfoLogin;
import com.example.auth.model.User;
import com.example.auth.service.AuthenticationService;

@RestController
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    public AuthController(){

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateDTO> autenticar(@RequestBody InfoLogin dadosLogin, @RequestHeader String Authorization){
        User user = authenticationService.authenticate(dadosLogin, Authorization);
        return new ResponseEntity<AuthenticateDTO>(AuthenticateDTO.toDTO(user, "Bearer "), HttpStatus.ACCEPTED);
    }
}