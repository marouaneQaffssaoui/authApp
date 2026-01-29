package com.example.authservice.domain.port.out;

import com.example.authservice.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    boolean existByEmail(String email);
}
