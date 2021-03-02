package com.example.auth.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.AuthenticateDTO;
import com.example.auth.dto.UserRegistryDTO;
import com.example.auth.model.User;
import com.example.auth.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public UserController(){

    }

    @PostMapping("/user")
    public ResponseEntity<AuthenticateDTO> registrate(@RequestBody UserRegistryDTO userRegistrationDTO){
        User user = userService.registrate(userRegistrationDTO.toUser());
        return  new ResponseEntity<AuthenticateDTO>(AuthenticateDTO.toDTO(user, "Bearer "), HttpStatus.CREATED);
    }

}