package com.example.websocketdemo.repository;

import com.example.websocketdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
    User save(User user);
    boolean existsByUsername(String username);
}
