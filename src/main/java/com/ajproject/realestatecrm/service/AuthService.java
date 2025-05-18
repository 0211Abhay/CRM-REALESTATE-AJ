package com.ajproject.realestatecrm.service;

import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.dto.AuthResponse;
import com.ajproject.realestatecrm.dto.LoginRequest;
import com.ajproject.realestatecrm.dto.RegisterRequest;
import com.ajproject.realestatecrm.repository.BrokerRepository;
import com.ajproject.realestatecrm.security.JwtUtils;
import com.ajproject.realestatecrm.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    
    @Autowired
    private BrokerRepository brokerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    public boolean checkEmailExists(String email) {
        return brokerRepository.existsByEmail(email);
    }
    
    public AuthResponse registerBroker(RegisterRequest registerRequest) throws Exception {
        // Check if passwords match
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new Exception("Passwords do not match");
        }
        
        // Check if email already exists
        if (checkEmailExists(registerRequest.getEmail())) {
            throw new Exception("Email is already in use");
        }
        
        // Create new broker account
        Broker broker = new Broker();
        broker.setName(registerRequest.getName());
        broker.setEmail(registerRequest.getEmail());
        broker.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        broker.setPhone(registerRequest.getPhone());
        
        LocalDateTime now = LocalDateTime.now();
        broker.setCreatedAt(now);
        broker.setUpdatedAt(now);
        
        brokerRepository.save(broker);
        
        // Authenticate the new broker
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        return new AuthResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail());
    }
    
    public AuthResponse loginBroker(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        return new AuthResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail());
    }
}
