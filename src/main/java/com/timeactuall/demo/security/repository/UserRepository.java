package com.timeactuall.demo.security.repository;

import com.timeactuall.demo.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
