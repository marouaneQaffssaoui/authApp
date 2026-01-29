package com.example.authservice.application.service;

import com.example.authservice.domain.model.Role;
import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.in.LoginUser;
import com.example.authservice.domain.port.in.RegisterUser;
import com.example.authservice.domain.port.out.UserRepository;
import com.example.authservice.infrastructure.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements RegisterUser, LoginUser {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder encoder,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return jwtProvider.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }

    @Override
    public void registerUser(String name, String lastname, String email, String password) {
        if (userRepository.existByEmail(email)) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User(
                UUID.randomUUID(),
                name,
                lastname,
                email,
                encoder.encode(password),
                Role.USER
        );

        userRepository.save(user);
    }
}
