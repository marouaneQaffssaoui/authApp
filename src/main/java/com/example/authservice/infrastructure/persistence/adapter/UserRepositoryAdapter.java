package com.example.authservice.infrastructure.persistence.adapter;

import com.example.authservice.domain.model.Role;
import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.out.UserRepository;
import com.example.authservice.infrastructure.persistence.entity.UserEntity;
import com.example.authservice.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository repository;

    public UserRepositoryAdapter(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name()
        );
        UserEntity savedUser = repository.save(entity);
        return new User(savedUser.getId(), savedUser.getName(), savedUser.getLastName(), savedUser.getEmail(), savedUser.getPassword(), Role.valueOf(savedUser.getRole()));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(e -> new User(e.getId(), e.getName(), e.getLastName(), e.getEmail(), e.getPassword(), Role.valueOf(e.getRole())));
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
