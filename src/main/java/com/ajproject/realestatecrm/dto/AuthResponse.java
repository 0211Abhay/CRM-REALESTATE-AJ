package com.ajproject.realestatecrm.dto;

public class AuthResponse {
    
    private String token;
    private String type = "Bearer";
    private Integer brokerId;
    private String name;
    private String email;
    private String phone;
    
    public AuthResponse(String token, Integer brokerId, String name, String email) {
        this.token = token;
        this.brokerId = brokerId;
        this.name = name;
        this.email = email;
    }
    
    public AuthResponse(String token, Integer brokerId, String name, String email, String phone) {
        this.token = token;
        this.brokerId = brokerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters and Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getBrokerId() {
        return brokerId;
    }
    
    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
