package com.management.backend.controller;

import java.util.Optional;

import com.management.backend.model.AuthenticationRequest;
import com.management.backend.model.AuthenticationResponse;
import com.management.backend.model.User;
import com.management.backend.repository.UserRepository;
import com.management.backend.service.CustomUserDetailsService;
import com.management.backend.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AutheticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService; 

    @Autowired
    JwtUtil jwt;

    @PostMapping("/api/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("wrong email or password");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());

        String token = jwt.generateToken(userDetails);
                
        return new AuthenticationResponse(token);
    }

    @GetMapping(value = "/api/currentAuthUser")
    public Optional<User> getCurrentUserName(Authentication authentication) {
        return this.userRepository.findByEmail(authentication.getName());
    }
} 