package com.example.auth.security;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User user = userRepository.findByEmail(email);

    if(user == null) {
      throw new UsernameNotFoundException("Usuário '" + email + "' não encontrado");
    }

    return org.springframework.security.core.userdetails.User
      .withUsername(email)
      .password(user.getPassword())
      .authorities(user.getRoles())
      .accountExpired(false)
      .accountLocked(false)
      .credentialsExpired(false)
      .disabled(false)
      .build();
  }
  
}
