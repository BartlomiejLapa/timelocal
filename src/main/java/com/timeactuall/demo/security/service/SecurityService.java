package com.timeactuall.demo.security.service;

public interface SecurityService {
    String findLoggedInEmail();

    void autoLogin(String email, String password);
}
