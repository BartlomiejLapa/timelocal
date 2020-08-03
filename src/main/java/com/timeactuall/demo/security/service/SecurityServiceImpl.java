//package com.timeactuall.demo.security.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//
//public class SecurityServiceImpl implements SecurityService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
//
//    @Override
//    public String findLoggedInEmail() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//            //wrocic do tego
//        }
//        return null;
//    }
//
//    @Override
//    public void autoLogin(String email, String password) {
//
//    }
//}
