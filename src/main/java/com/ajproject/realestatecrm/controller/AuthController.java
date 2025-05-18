package com.ajproject.realestatecrm.controller;

import com.ajproject.realestatecrm.dto.AuthResponse;
import com.ajproject.realestatecrm.dto.LoginRequest;
import com.ajproject.realestatecrm.dto.RegisterRequest;
import com.ajproject.realestatecrm.dto.ProfileUpdateRequest;
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
    
    @GetMapping("/check")
    public ResponseEntity<?> checkSession(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            return ResponseEntity.ok(authService.checkSession(authHeader));
        } catch (Exception e) {
            return ResponseEntity.ok().body(
                java.util.Map.of(
                    "authenticated", false,
                    "message", e.getMessage()
                )
            );
        }
    }
    
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody ProfileUpdateRequest profileUpdateRequest) {
        try {
            return ResponseEntity.ok(authService.updateProfile(authHeader, profileUpdateRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of(
                    "success", false,
                    "message", e.getMessage()
                )
            );
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Since we're using JWT, server-side logout is minimal
        // The client will remove the token
        return ResponseEntity.ok().body(
            java.util.Map.of(
                "success", true,
                "message", "Logged out successfully"
            )
        );
    }
}
