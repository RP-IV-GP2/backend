package com.example.auth.service;



import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.dto.InfoLogin;
import com.example.auth.exceptions.DuplicateEmailException;
import com.example.auth.exceptions.InvalidTokenException;
import com.example.auth.exceptions.TimeoutTokenException;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;

import java.util.Date;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    public User authenticate(InfoLogin infos, String token){
        User user = userRepository.findByEmail(infos.getEmail()).orElseThrow(DuplicateEmailException::new);
        if(infos.getSenha().equals(user.getPassword()) && !token.isEmpty() && validate(token)) {
            return user;
        }
        else {
            throw new DuplicateEmailException();
        }
    }

    private boolean validate(String token) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);

            System.out.println(claims.getIssuer());
            System.out.println(claims.getIssuedAt());
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new TimeoutTokenException();
            System.out.println(claims.getExpiration());
            return true;
        } catch (TimeoutTokenException et){
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }

    }


}
