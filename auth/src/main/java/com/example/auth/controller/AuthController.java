package com.example.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.UserDTO;
import com.example.auth.jwt.TokenProvider;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;

@RestController
@RequestMapping("/login")
public class AuthController {
	private AuthenticationManager authenticationManager;
	private TokenProvider tokenProvider;
	private UserRepository userRepository;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, TokenProvider tokenProvider,
			UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userRepository = userRepository;
	}

	@RequestMapping("/testeSecurity")
	public String teste() {
		return "testado";
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
			"application/json", "application/xml", "application/x-yaml"	})
	public ResponseEntity<?> login(@RequestBody UserDTO userDto) {
		try {
			String userName = userDto.getUserName();
			String password = userDto.getPassword();
			String token = "";
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			User user = userRepository.findByUserName(userName);

			if (user != null) {
				token = tokenProvider.createToken(userName, user.getRoles());
			} else {
				throw new UsernameNotFoundException("User name not found");
			}
			Map<Object, Object> model = new HashMap<>();
			model.put("username", userName);
			model.put("token", token);
			return ResponseEntity.ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid User or Password");
		}
	}
}
