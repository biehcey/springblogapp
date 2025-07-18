package com.example.springblogapi.repository;

import com.example.springblogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);

    User findByUsernameAndPassword(String username, String password);
}
