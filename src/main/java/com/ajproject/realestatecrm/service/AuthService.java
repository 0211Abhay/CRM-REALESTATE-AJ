package com.ajproject.realestatecrm.service;

import com.ajproject.realestatecrm.beans.Broker;
import com.ajproject.realestatecrm.dto.AuthResponse;
import com.ajproject.realestatecrm.dto.LoginRequest;
import com.ajproject.realestatecrm.dto.RegisterRequest;
import com.ajproject.realestatecrm.dto.ProfileUpdateRequest;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        
        return new AuthResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail(), userDetails.getPhone());
    }
    
    public AuthResponse loginBroker(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        return new AuthResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail(), userDetails.getPhone());
    }
    
    public Map<String, Object> checkSession(String authHeader) {
        Map<String, Object> response = new HashMap<>();
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("authenticated", false);
            response.put("message", "No token provided");
            return response;
        }
        
        String token = authHeader.substring(7);
        boolean isValid = jwtUtils.validateJwtToken(token);
        
        if (!isValid) {
            response.put("authenticated", false);
            response.put("message", "Invalid or expired token");
            return response;
        }
        
        String email = jwtUtils.getUsernameFromJwtToken(token);
        Optional<Broker> brokerOpt = brokerRepository.findByEmail(email);
        
        if (brokerOpt.isEmpty()) {
            response.put("authenticated", false);
            response.put("message", "Broker not found");
            return response;
        }
        
        Broker broker = brokerOpt.get();
        Map<String, Object> brokerMap = new HashMap<>();
        brokerMap.put("id", broker.getBrokerId());
        brokerMap.put("brokerId", broker.getBrokerId()); 
        brokerMap.put("name", broker.getName());
        brokerMap.put("email", broker.getEmail());
        brokerMap.put("phone", broker.getPhone());
        
        response.put("authenticated", true);
        response.put("broker", brokerMap);
        
        return response;
    }
    
    public Map<String, Object> updateProfile(String authHeader, ProfileUpdateRequest profileUpdateRequest) throws Exception {
        Map<String, Object> response = new HashMap<>();
        
        // Validate token and get broker
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new Exception("No token provided");
        }
        
        String token = authHeader.substring(7);
        boolean isValid = jwtUtils.validateJwtToken(token);
        
        if (!isValid) {
            throw new Exception("Invalid or expired token");
        }
        
        String email = jwtUtils.getUsernameFromJwtToken(token);
        Optional<Broker> brokerOpt = brokerRepository.findByEmail(email);
        
        if (brokerOpt.isEmpty()) {
            throw new Exception("Broker not found");
        }
        
        Broker broker = brokerOpt.get();
        boolean emailChanged = false;
        
        // Check if email is being changed and if it's already in use by another broker
        if (!broker.getEmail().equals(profileUpdateRequest.getEmail())) {
            boolean emailExists = brokerRepository.existsByEmail(profileUpdateRequest.getEmail());
            if (emailExists) {
                throw new Exception("Email is already in use by another broker");
            }
            emailChanged = true;
        }
        
        // Update broker information
        broker.setName(profileUpdateRequest.getName());
        broker.setEmail(profileUpdateRequest.getEmail());
        broker.setPhone(profileUpdateRequest.getPhone());
        broker.setUpdatedAt(LocalDateTime.now());
        
        // Save updated broker
        brokerRepository.save(broker);
        
        // Prepare response
        Map<String, Object> updatedBrokerMap = new HashMap<>();
        updatedBrokerMap.put("id", broker.getBrokerId());
        updatedBrokerMap.put("brokerId", broker.getBrokerId());
        updatedBrokerMap.put("name", broker.getName());
        updatedBrokerMap.put("email", broker.getEmail());
        updatedBrokerMap.put("phone", broker.getPhone());
        
        response.put("success", true);
        response.put("message", "Profile updated successfully");
        response.put("broker", updatedBrokerMap);
        
        // Indicate if email was changed
        if (emailChanged) {
            response.put("emailChanged", true);
            response.put("message", "Email updated successfully. You will need to log in again with your new email.");
        }
        
        return response;
    }
}
