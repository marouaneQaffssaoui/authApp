package com.example.authservice.infrastructure.web.dto;

public class LoginResponse {
    public String token;
    public LoginResponse(String token) {
        this.token = token;
    }
}
