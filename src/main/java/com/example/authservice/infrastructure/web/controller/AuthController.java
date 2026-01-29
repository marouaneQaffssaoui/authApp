package com.example.authservice.infrastructure.web.controller;

import com.example.authservice.domain.port.in.LoginUser;
import com.example.authservice.domain.port.in.RegisterUser;
import com.example.authservice.infrastructure.web.dto.LoginRequest;
import com.example.authservice.infrastructure.web.dto.LoginResponse;
import com.example.authservice.infrastructure.web.dto.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegisterUser registerUser;
    private final LoginUser loginUser;

    public AuthController(RegisterUser registerUser, LoginUser loginUser) {
        this.registerUser = registerUser;
        this.loginUser = loginUser;
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDto request) {
        registerUser.registerUser(request.getName(), request.getLastName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = loginUser.loginUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
