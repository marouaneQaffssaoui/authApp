package com.example.authservice.infrastructure.web.dto;

import com.example.authservice.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterDto {
    @Email
    @NotBlank
    private String email;
    private String name;
    private String lastName;
    private String role;
    private String password;

    public RegisterDto(String email, String name, String lastName, String role, String password) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
    }

    public RegisterDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
