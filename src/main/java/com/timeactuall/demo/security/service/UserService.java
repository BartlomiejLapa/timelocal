package com.timeactuall.demo.security.service;

import com.timeactuall.demo.security.model.User;
import com.timeactuall.demo.security.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  {
    User findByEmail(String email);
    User save(UserRegistrationDto registrationDto);
}
