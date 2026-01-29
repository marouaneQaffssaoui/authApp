package com.example.authservice.domain.port.in;

public interface RegisterUser {
    void registerUser(String name, String lastname, String email, String password);
}
