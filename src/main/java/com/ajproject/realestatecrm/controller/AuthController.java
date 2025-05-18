package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.dto.AuthResponse;
import com.ajproject.realestatecrm.dto.LoginRequest;
import com.ajproject.realestatecrm.dto.RegisterRequest;
import com.ajproject.realestatecrm.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerBroker(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            AuthResponse response = authService.registerBroker(registerRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginBroker(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse response = authService.loginBroker(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }
}
